package com.silverhetch.calisto.storage;

import java.io.File;
import java.net.URI;

class StorageFileImpl implements StorageFile {
    private final File root;

    StorageFileImpl(File root) {
        this.root = root;
    }

    @Override
    public String id() {
        return root.getName();
    }

    @Override
    public URI uri() {
        return root.toURI();
    }

    @Override
    public void delete() {
        delete(root);
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            String files[] = file.list();
            for (String temp : files) {
                File fileDelete = new File(file, temp);
                delete(fileDelete);
            }
        }
        file.delete();
    }
}
