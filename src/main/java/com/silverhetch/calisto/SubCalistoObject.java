package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.net.URI;

class SubCalistoObject implements CalistoObject {
    private final Object parentObject;
    private final File rootFile;

    SubCalistoObject(Object parentObject, URI subFileUri) {
        this.parentObject = parentObject;
        this.rootFile = new File(subFileUri);
    }

    @Override
    public String name() {
        return rootFile.getName();
    }

    @Override
    public CalistoObject[] subFiles() {
        return new SubCalistoFileFactory().subFiles(parentObject, rootFile);
    }

    @Override
    public String toString() {
        return name();
    }
}
