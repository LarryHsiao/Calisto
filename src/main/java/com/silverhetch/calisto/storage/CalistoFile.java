package com.silverhetch.calisto.storage;

import java.io.File;

public interface CalistoFile {
    String id();
    File file();
    void delete();
}
