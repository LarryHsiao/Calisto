package com.silverhetch.calisto.storage;

import java.io.File;

class CalistoFileImpl implements CalistoFile {
    private final File root;

    CalistoFileImpl(File root) {
        this.root = root;
    }

    @Override
    public String id() {
        return root.getName();
    }

    @Override
    public File file() {
        return root;
    }

    @Override
    public void delete() {
        delete(root);
    }

    public static void delete(File file) {
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
