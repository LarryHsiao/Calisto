package com.silverhetch.calisto.storage;

import java.io.File;

public interface Storage {
    StorageFile save(File file) throws Exception;

    StorageFile fileById(String id);

    StorageFile fileByUri(String uri);

    String path();
}
