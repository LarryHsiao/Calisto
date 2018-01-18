package com.silverhetch.calisto;

import com.silverhetch.calisto.storage.Storage;
import com.silverhetch.calisto.tagging.Object;

class CalistoFileImpl implements CalistoFile {
    private final Object object;
    private final Storage storage;

    CalistoFileImpl(Storage storage, Object object) {
        this.storage = storage;
        this.object = object;
    }

    @Override
    public String name() {
        return object.name();
    }

    @Override
    public CalistoFile[] subFiles() {
        return null;
    }

}
