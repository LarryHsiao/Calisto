package com.silverhetch.calisto.config;

import java.io.File;

class ConfigurationImpl implements Configuration {
    @Override
    public File workspaceFile() {
        File rootFile = new File(System.getProperty("user.home") + File.separator + ".Calisto" + File.separator);
        if (!rootFile.exists() &&  !rootFile.mkdirs()) {
            throw new RuntimeException("Can`t not create root file");
        }
        return rootFile;
    }
}
