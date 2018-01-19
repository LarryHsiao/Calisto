package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;

 class SubCalistoFileFactory {
     CalistoObject[] subFiles(Object object, File rootFile) {
        File[] subFiles = rootFile.listFiles();
        if (rootFile.isDirectory() && subFiles != null) {
            CalistoObject[] calistoObjects = new SubCalistoObject[subFiles.length];
            for (int i = 0; i < subFiles.length; i++) {
                calistoObjects[i] = new SubCalistoObject(object, subFiles[i].toURI());
            }
            return calistoObjects;
        }
        return new CalistoObject[0];
    }
}
