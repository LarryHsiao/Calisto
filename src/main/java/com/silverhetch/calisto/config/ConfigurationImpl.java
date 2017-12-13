package com.silverhetch.calisto.config;

import java.io.File;

class ConfigurationImpl implements Configuration {
    @Override
    public File workspaceFile() {
        return new File(System.getProperty("user.home") + File.separator + ".Calisto" + File.separator);
    }
}
