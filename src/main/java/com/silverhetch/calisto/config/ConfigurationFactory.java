package com.silverhetch.calisto.config;

public class ConfigurationFactory {
    public Configuration config() {
        return new JsonConfiguration(new DefaultConfiguration());
    }
}
