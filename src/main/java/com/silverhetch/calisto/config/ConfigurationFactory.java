package com.silverhetch.calisto.config;

import com.silverhetch.calisto.config.file.JsonFile;

import java.io.File;

public class ConfigurationFactory {
    public Configuration config() {
        return new JsonConfiguration(
                new JsonFile(new File(System.getProperty("user.home") + File.separator + ".calisto.config")),
                new DefaultConfiguration()
        );
    }
}
