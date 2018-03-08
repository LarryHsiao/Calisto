package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.tagging.Object;

import java.io.File;

class SubCalistoFileFactory {
    CalistoFile[] subFiles(Object object, File rootFile, ExecutableFactory factory) {
        File[] subFiles = rootFile.listFiles();
        if (rootFile.isDirectory() && subFiles != null) {
            CalistoFile[] calistoFiles = new SubCalistoFile[subFiles.length];
            for (int i = 0; i < subFiles.length; i++) {
                calistoFiles[i] = new SubCalistoFile(object, subFiles[i].toURI(), factory);
            }
            return calistoFiles;
        }
        return new CalistoFile[0];
    }
}
