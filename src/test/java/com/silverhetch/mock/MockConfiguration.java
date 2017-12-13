package com.silverhetch.mock;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;

public class MockConfiguration implements Configuration {
    @Override
    public File workspaceFile() {
        return new File(System.getProperty("user.home"),".CalistoTesting");
    }
}
