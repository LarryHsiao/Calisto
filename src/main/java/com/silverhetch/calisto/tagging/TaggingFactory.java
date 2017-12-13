package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.Database;

public class TaggingFactory {
    private final Database database;

    public TaggingFactory(Database database) {
        this.database = database;
    }

    public Tags tags() {
        return new DatabaseTags(database);
    }

    public Objects objects() {
        return new DatabaseObjects(database);
    }
}
