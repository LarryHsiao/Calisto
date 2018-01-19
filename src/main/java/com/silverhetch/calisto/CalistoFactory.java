package com.silverhetch.calisto;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.config.ConfigurationFactory;
import com.silverhetch.calisto.storage.StorageFactory;
import com.silverhetch.calisto.tagging.TaggingFactory;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;

public class CalistoFactory {
    private final Configuration configuration;

    public CalistoFactory() {
        this.configuration = new ConfigurationFactory().config();
    }

    public CalistoObjects calisto() {
        return new CalistoObjectsImpl(
                new StorageFactory(configuration).storage(),
                new TaggingFactory(new DatabaseFactory().database()).objects()
        );
    }
}
