package com.silverhetch.calisto.tagging.database;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.config.ConfigurationFactory;

public class DatabaseFactory {
    private final Configuration configuration;

    public DatabaseFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Database database() {
        return new SQLiteDatabase(configuration);
    }
}
