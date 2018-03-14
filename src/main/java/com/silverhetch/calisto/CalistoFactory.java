package com.silverhetch.calisto;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.config.ConfigurationFactory;
import com.silverhetch.calisto.javafx.utility.file.ExecutableFactory;
import com.silverhetch.calisto.storage.StorageFactory;
import com.silverhetch.calisto.tagging.TaggingFactory;
import com.silverhetch.calisto.tagging.Tags;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;

public class CalistoFactory {
    private final Configuration configuration;

    public CalistoFactory() {
        this.configuration = new ConfigurationFactory().config();
    }

    public CalistoFiles objects() {
        return new CalistoFilesImpl(
                new StorageFactory(configuration).storage(),
                new TaggingFactory(new DatabaseFactory(configuration).database()).objects(),
                new ExecutableFactory()
        );
    }

    public Tags tags() {
        return new CalistoTagsImpl(
                new TaggingFactory(new DatabaseFactory(configuration).database()).tags()
        );
    }
}
