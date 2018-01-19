package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.net.URI;

class CalistoObjectImpl implements CalistoObject {
    private final Object object;

    CalistoObjectImpl(Object object) {
        this.object = object;
    }

    @Override
    public String name() {
        return object.name();
    }

    @Override
    public CalistoObject[] subFiles() {
        final File rootFile = new File(URI.create(object.objectUri()).getPath());
        return new SubCalistoFileFactory().subFiles(object,rootFile);
    }

}