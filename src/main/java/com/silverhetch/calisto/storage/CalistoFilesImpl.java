package com.silverhetch.calisto.storage;

import java.io.File;

class CalistoFilesImpl implements CalistoFiles {
    private final File file;

    CalistoFilesImpl(File file) {
        this.file = file;
    }
}
