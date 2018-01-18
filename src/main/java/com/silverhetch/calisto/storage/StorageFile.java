package com.silverhetch.calisto.storage;

import java.io.File;
import java.net.URI;

public interface StorageFile {
    String id();
    URI uri();
    void delete();
}
