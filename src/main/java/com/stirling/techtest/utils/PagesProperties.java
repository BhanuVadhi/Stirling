package com.stirling.techtest.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum PagesProperties {
    INSTANCE;

    private final Properties properties;

    PagesProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("pages.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getURL() {
        return properties.getProperty("URL");
    }
    public String getUsername() {
        return properties.getProperty("Username");
    }
    public String getPassword() {
        return properties.getProperty("Password");
    }
}

