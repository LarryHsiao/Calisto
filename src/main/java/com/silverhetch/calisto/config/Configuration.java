package com.silverhetch.calisto.config;

import java.io.File;

public interface Configuration {
    File changeHome(String path);
    File workspaceFile();
}
