package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.tagging.AttachedTags;
import com.silverhetch.calisto.tagging.Object;

import java.io.File;
import java.io.IOException;
import java.net.URI;

class CalistoFileImpl implements CalistoFile {
    private final Object object;
    private final Storage storage;
    private final ExecutableFactory executableFactory;

    CalistoFileImpl(Storage storage, Object object, ExecutableFactory executableFactory) {
        this.object = object;
        this.storage = storage;
        this.executableFactory = executableFactory;
    }

    @Override
    public String name() {
        return object.name();
    }

    @Override
    public CalistoFile[] subFiles() {
        final File rootFile = new File(URI.create(object.objectUri()).getPath());
        return new SubCalistoFileFactory().subFiles(object, rootFile, executableFactory);
    }

    @Override
    public AttachedTags attachedTags() {
        return object.tags();
    }

    @Override
    public void execute() throws IOException {
        executableFactory.file().execute(URI.create(object.objectUri()));
    }

    @Override
    public void delete() {
        object.delete();
        storage.fileByUri(object.objectUri()).delete();
    }

    @Override
    public String toString() {
        return name();
    }
}
