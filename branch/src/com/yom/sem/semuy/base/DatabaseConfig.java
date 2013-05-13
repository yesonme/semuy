package com.yom.sem.semuy.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private Properties properties;
    private FileInputStream inputFile;

    public DatabaseConfig() {
        properties = new Properties();
    }

    public DatabaseConfig(String filePath) {
        properties = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            properties.load(inputFile);
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getValue(String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }

        return "";
    }
}