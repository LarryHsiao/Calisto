package com.silverhetch.calisto.storage;

import java.io.File;

public interface Storage {
    CalistoFile save(File file);

    CalistoFile get(String id);

    String path();
}
