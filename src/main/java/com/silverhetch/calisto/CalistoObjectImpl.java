package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        return new SubCalistoFileFactory().subFiles(object, rootFile);
    }

    @Override
    public void execute() throws IOException {
        Desktop.getDesktop().open(new File(URI.create(object.objectUri())));
    }

    @Override
    public String toString() {
        return name();
    }
}
