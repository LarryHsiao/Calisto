package com.silverhetch.calisto.storage;

import java.io.File;

public interface Storage {
    /**
     * Store files/directories with File Object in parameter,
     * this method should store all the file to inner file system.
     *
     * @return A {@link CalistoFiles} which is files we stored.
     */
    CalistoFiles save(File file);

    String path();
}
