package com.silverhetch.calisto.storage;

import java.io.File;

public interface Storage {
    /**
     * Store files/directories with File Object in parameter,
     * this method should store all the file to inner file system.
     *
     * @return A {@link CalistoFile} which is file we stored.
     */
    CalistoFile save(File file);
}
