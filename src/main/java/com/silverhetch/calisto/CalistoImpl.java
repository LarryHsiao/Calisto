package com.silverhetch.calisto;

import com.silverhetch.calisto.storage.CalistoFile;
import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.tagging.Object;
import com.silverhetch.calisto.tagging.Objects;

import java.io.File;

class CalistoImpl implements Calisto {
    private final Storage storage;
    private final Objects objects;

    CalistoImpl(Storage storage, Objects objects) {
        this.storage = storage;
        this.objects = objects;
    }

    @Override
    public Object put(File file, String... tags) {
        final CalistoFile calistoFile = storage.save(file);
        return objects.add(calistoFile.file().getName(), calistoFile.file().toURI().toString());
    }
}
