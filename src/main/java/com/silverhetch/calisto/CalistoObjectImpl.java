package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.io.IOException;
import java.net.URI;

class CalistoObjectImpl implements CalistoObject {
    private final Object object;
    private final ExecutableFactory executableFactory;

    CalistoObjectImpl(Object object, ExecutableFactory executableFactory) {
        this.object = object;
        this.executableFactory = executableFactory;
    }

    @Override
    public String name() {
        return object.name();
    }

    @Override
    public CalistoObject[] subFiles() {
        final File rootFile = new File(URI.create(object.objectUri()).getPath());
        return new SubCalistoFileFactory().subFiles(object, rootFile, executableFactory);
    }

    @Override
    public void attachTag(CalistoTag tag) {
        // TODO: 3/6/2018
    }

    @Override
    public void execute() throws IOException {
        executableFactory.file().execute(URI.create(object.objectUri()));
    }

    @Override
    public String toString() {
        return name();
    }
}
