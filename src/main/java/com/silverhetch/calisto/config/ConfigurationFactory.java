package com.silverhetch.calisto.config;

import com.silverhetch.calisto.config.file.JsonFile;

import java.io.File;

public class ConfigurationFactory {
    private final static File DEFAULT_CONFIG;
    private final static File[] LOADING_CANDIDATE;

    static {
        DEFAULT_CONFIG = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        LOADING_CANDIDATE = new File[]{
                new File(System.getProperty("user.home") + File.separator + ".calisto.config"),
        };
    }

    public Configuration config() {
        return new JsonConfiguration(
                new JsonFile(validConfigJsonPath()),
                new DefaultConfiguration()
        );
    }

    private String validConfigJsonPath() {
        for (File file : LOADING_CANDIDATE) {
            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }
        return DEFAULT_CONFIG.getAbsolutePath();
    }
}
