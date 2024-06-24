package com.api.automation.utils;


import com.api.automation.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {

    private static Properties property;

    // Singleton design pattern
    public static Properties getInstance() {
        if (property == null) {
            property = new Properties();
        }
        return property;
    }

    static void loadProperties() throws Exception {
        try (FileInputStream input = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
            getInstance().load(input);
        } catch (IOException e) {
            throw new Exception("IOException occurred while loading Property file in the specified path");
        }
    }

    public static String getPropertyValue(String key) throws Exception {
        loadProperties();
        if (Objects.isNull(property.getProperty(key.toLowerCase()))) {
            throw new Exception("Property name - " + key + " is not found. Please check the config.properties");
        }
        return property.getProperty(key.toLowerCase());
    }
}
