package com.silverhetch.calisto;

import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.storage.StorageFile;
import com.silverhetch.calisto.tagging.Object;
import com.silverhetch.calisto.tagging.Objects;

import java.io.File;

class CalistoFilesImpl implements CalistoFiles {
    private final Storage storage;
    private final Objects objects;

    CalistoFilesImpl(Storage storage, Objects objects) {
        this.storage = storage;
        this.objects = objects;
    }

    @Override
    public CalistoFile put(File file, String... tags)  throws Exception{
        final StorageFile storageFile = storage.save(file);
        Object object = objects.add(file.getName(), storageFile.uri().toString());
        return new CalistoFileImpl(storage, object);
    }

    @Override
    public CalistoFile[] all() {
        Object[] objectArray = objects.all();
        CalistoFile[] calistoFiles = new CalistoFile[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            calistoFiles[i] = new CalistoFileImpl(storage, objectArray[i]);
        }
        return calistoFiles;
    }
}
