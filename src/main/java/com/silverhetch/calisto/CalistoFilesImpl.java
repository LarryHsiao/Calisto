package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.storage.StorageFile;
import com.silverhetch.calisto.tagging.Object;
import com.silverhetch.calisto.tagging.Objects;

import java.io.File;

class CalistoFilesImpl implements CalistoFiles {
    private final Storage storage;
    private final Objects objects;
    private final ExecutableFactory factory;

    CalistoFilesImpl(Storage storage, Objects objects, ExecutableFactory factory) {
        this.storage = storage;
        this.objects = objects;
        this.factory = factory;
    }

    @Override
    public CalistoFile put(File file, String... tags) throws Exception {
        final StorageFile storageFile = storage.save(file);
        Object object = objects.add(file.getName(), storageFile.uri().toString());
        for (String tag : tags) {
            object.tags().addTag(tag, "");
        }
        return new CalistoFileImpl(object, factory);
    }

    @Override
    public CalistoFile[] byTag(String tagName) {
        Object[] result = objects.byTagName(tagName);
        CalistoFile[] calistoFile = new CalistoFile[result.length];
        for (int i = 0; i < calistoFile.length; i++) {
            calistoFile[i] = new CalistoFileImpl(result[i], factory);
        }
        return calistoFile;
    }

    @Override
    public CalistoFile[] all() {
        Object[] objectArray = objects.all();
        CalistoFile[] calistoFiles = new CalistoFile[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            calistoFiles[i] = new CalistoFileImpl(objectArray[i], factory);
        }
        return calistoFiles;
    }
}
