package com.silverhetch.calisto;

import java.io.File;
import java.net.URI;

public class SubCalistoFile implements CalistoFile {
    private final File rootFile;

    public SubCalistoFile(URI uri) {
        this.rootFile = new File(uri);
    }

    @Override
    public String name() {
        return rootFile.getName();
    }

    @Override
    public CalistoFile[] subFiles() {
        File[] subFiles = rootFile.listFiles();
        if (rootFile.isDirectory() && subFiles != null) {
            CalistoFile[] calistoFiles = new SubCalistoFile[subFiles.length];
            for (int i = 0; i < subFiles.length; i++) {
                calistoFiles[i] = new SubCalistoFile(subFiles[i].toURI());
            }
            return calistoFiles;
        }
        return new CalistoFile[0];
    }
}
