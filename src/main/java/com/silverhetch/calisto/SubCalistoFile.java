package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.tagging.AttachedTags;
import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.io.IOException;
import java.net.URI;

class SubCalistoFile implements CalistoFile {
    private final Object parentObject;
    private final File rootFile;
    private final ExecutableFactory factory;

    SubCalistoFile(Object parentObject, URI subFileUri, ExecutableFactory factory) {
        this.parentObject = parentObject;
        this.rootFile = new File(subFileUri);
        this.factory = factory;
    }

    @Override
    public String name() {
        return rootFile.getName();
    }

    @Override
    public CalistoFile[] subFiles() {
        return new SubCalistoFileFactory().subFiles(parentObject, rootFile, factory);
    }

    @Override
    public void execute() throws IOException {
        factory.directory().execute(rootFile.toURI());
    }

    @Override
    public AttachedTags attachedTags() {
        return parentObject.tags();
    }

    @Override
    public String toString() {
        return name();
    }
}
