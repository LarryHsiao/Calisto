package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.io.IOException;
import java.net.URI;

class SubCalistoObject implements CalistoObject {
    private final Object parentObject;
    private final File rootFile;
    private final ExecutableFactory factory;

    SubCalistoObject(Object parentObject, URI subFileUri, ExecutableFactory factory) {
        this.parentObject = parentObject;
        this.rootFile = new File(subFileUri);
        this.factory = factory;
    }

    @Override
    public String name() {
        return rootFile.getName();
    }

    @Override
    public CalistoObject[] subFiles() {
        return new SubCalistoFileFactory().subFiles(parentObject, rootFile, factory);
    }

    @Override
    public void execute() throws IOException {
        factory.directory().execute(rootFile.toURI());
    }

    @Override
    public String toString() {
        return name();
    }
}
