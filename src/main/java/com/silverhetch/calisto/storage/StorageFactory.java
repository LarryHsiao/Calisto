package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;

public class StorageFactory {
    private final Configuration configuration;

    public StorageFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Storage storage() {
        return new StorageImpl(configuration);
    }
}
