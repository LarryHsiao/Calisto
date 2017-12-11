package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;

class StorageImpl implements Storage {

    private final Configuration configuration;

    public StorageImpl(Configuration configuration){
        this.configuration = configuration;
    }


    @Override
    public CalistoFile save(File file) {
        return null;
    }
}
