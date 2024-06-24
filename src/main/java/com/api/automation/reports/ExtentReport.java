package com.api.automation.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

import static com.api.automation.constants.FrameworkConstants.extentReportPath;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport {

  private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportPath);
  private static ExtentReports extentReports;

  /**
   * This method is to initialize the Extent Report
   */
  public static void initExtentReport() throws Exception {
    try {
      if (Objects.isNull(extentReports)) {
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        InetAddress ip = InetAddress.getLocalHost();
        String hostname = ip.getHostName();
        extentReports.setSystemInfo("Host Name", hostname);
        extentReports.setSystemInfo("Environment", "API Automation - RestAssured");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentSparkReporter.config().setDocumentTitle("HTML Report");
        extentSparkReporter.config().setReportName("API Automation Test");
        extentSparkReporter.config().setTheme(Theme.DARK);
      }
    } catch (Exception e) {
      throw new Exception("Failed to initialize extent report - " + e.getMessage());
    }
  }

  public static void createTest(String testCaseName) {
    ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
  }

  public static void flushExtentReport() {
    if (Objects.nonNull(extentReports)) {
      extentReports.flush();
    }
    ExtentManager.unload();

    //TODO comment out below once you install "sudo apt-get install xvfb" . for now I dont need graphical UI.
    // else you will get an error for No X11 DISPLAY variable was set,
//    try {
//      Desktop.getDesktop().browse(new File(extentReportPath).toURI());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }
}
