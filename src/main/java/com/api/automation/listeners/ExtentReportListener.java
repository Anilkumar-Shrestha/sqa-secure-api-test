package com.api.automation.listeners;

import com.api.automation.reports.ExtentLogger;
import com.api.automation.reports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener, ISuiteListener {

  private static final String MESSAGE = "Test - <b>";

  @Override
  public void onStart(ISuite suite) {
      try {
          ExtentReport.initExtentReport();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
  }

  @Override
  public void onFinish(ISuite suite) {
    ExtentReport.flushExtentReport();
  }

  @Override
  public void onTestStart(ITestResult result) {
    ExtentReport.createTest(result.getMethod().getMethodName());
    ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b>  is started");
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b> is passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ExtentLogger.logFailureDetails(MESSAGE + result.getMethod().getMethodName() + "</b> is failed");
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ExtentLogger.skip(MESSAGE + result.getMethod().getMethodName() + "</b> is skipped");
  }
}
