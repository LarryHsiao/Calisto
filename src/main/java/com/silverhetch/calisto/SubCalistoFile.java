package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.net.URI;

class SubCalistoFile implements CalistoFile {
    private final Object parentObject;
    private final File rootFile;

    SubCalistoFile(Object parentObject, URI subFileUri) {
        this.parentObject = parentObject;
        this.rootFile = new File(subFileUri);
    }

    @Override
    public String name() {
        return rootFile.getName();
    }

    @Override
    public CalistoFile[] subFiles() {
        return new SubCalistoFileFactory().subFiles(parentObject, rootFile);
    }
}
