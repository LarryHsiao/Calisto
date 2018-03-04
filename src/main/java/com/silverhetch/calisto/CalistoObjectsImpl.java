package com.silverhetch.calisto;

import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.storage.StorageFile;
import com.silverhetch.calisto.tagging.Object;
import com.silverhetch.calisto.tagging.Objects;

import java.io.File;

class CalistoObjectsImpl implements CalistoObjects {
    private final Storage storage;
    private final Objects objects;
    private final ExecutableFactory factory;

    CalistoObjectsImpl(Storage storage, Objects objects, ExecutableFactory factory) {
        this.storage = storage;
        this.objects = objects;
        this.factory = factory;
    }

    @Override
    public CalistoObject put(File file, String... tags) throws Exception {
        final StorageFile storageFile = storage.save(file);
        Object object = objects.add(file.getName(), storageFile.uri().toString());
        for (String tag : tags) {
            object.tags().addTag(tag, "");
        }
        return new CalistoObjectImpl(object, factory);
    }

    @Override
    public CalistoObject[] byTag(String tagName) {
        Object[] result = objects.byTagName(tagName);
        CalistoObject[] calistoObject = new CalistoObject[result.length];
        for (int i = 0; i < calistoObject.length; i++) {
            calistoObject[i] = new CalistoObjectImpl(result[i], factory);
        }
        return calistoObject;
    }

    @Override
    public CalistoObject[] all() {
        Object[] objectArray = objects.all();
        CalistoObject[] calistoObjects = new CalistoObject[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            calistoObjects[i] = new CalistoObjectImpl(objectArray[i], factory);
        }
        return calistoObjects;
    }
}
