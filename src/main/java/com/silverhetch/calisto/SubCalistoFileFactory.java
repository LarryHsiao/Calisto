package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;

public class SubCalistoFileFactory {
    public CalistoFile[] subFiles(Object object, File rootFile) {
        File[] subFiles = rootFile.listFiles();
        if (rootFile.isDirectory() && subFiles != null) {
            CalistoFile[] calistoFiles = new SubCalistoFile[subFiles.length];
            for (int i = 0; i < subFiles.length; i++) {
                calistoFiles[i] = new SubCalistoFile(object, subFiles[i].toURI());
            }
            return calistoFiles;
        }
        return new CalistoFile[0];
    }
}
