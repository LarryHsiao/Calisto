package com.silverhetch.calisto.tagging.database;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.config.ConfigurationFactory;

public class DatabaseFactory {

    public Database database() {
        return new SQLiteDatabase(new ConfigurationFactory().config());
    }
}
