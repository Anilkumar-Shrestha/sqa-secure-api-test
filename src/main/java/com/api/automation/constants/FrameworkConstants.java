package com.api.automation.constants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.File;

public class FrameworkConstants {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_FOLDER_PATH = PROJECT_PATH + File.separator + "src" + File.separator+ "test" + File.separator + "resources";
    public static final String CONFIG_PROPERTIES_PATH = RESOURCES_FOLDER_PATH + File.separator + "config.properties";
    public static final String extentReportPath = PROJECT_PATH + File.separator + "extent-test-report"+ File.separator + getCurrentDateTime() + File.separator + "index.html";



    private static String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
