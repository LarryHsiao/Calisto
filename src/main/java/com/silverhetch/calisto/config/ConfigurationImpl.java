package com.silverhetch.calisto.config;

import java.io.File;

public class ConfigurationImpl implements Configuration {
    @Override
    public File workspaceFile() {
        File file = new File(System.getProperty("user.home") + File.separator + ".Calisto" + File.separator);
        file.mkdirs();
        return file;
    }
}
