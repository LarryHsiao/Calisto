package com.silverhetch.mock;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;

public class MockConfiguration implements Configuration {
    @Override
    public File changeHome(String path) {
        throw new RuntimeException("not allow here");
    }

    @Override
    public File workspaceFile() {
        return new File(System.getProperty("user.home"),".CalistoTesting");
    }
}
