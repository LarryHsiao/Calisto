package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.net.URI;

class CalistoFileImpl implements CalistoFile {
    private final Object object;

    CalistoFileImpl(Object object) {
        this.object = object;
    }

    @Override
    public String name() {
        return object.name();
    }

    @Override
    public CalistoFile[] subFiles() {
        final File rootFile = new File(URI.create(object.objectUri()).getPath());
        return new SubCalistoFileFactory().subFiles(object,rootFile);
    }

}
