package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

class DatabaseObject implements Object {
    private final Database database;
    private final long id;
    private final String name;
    private final String uri;

    public DatabaseObject(Database database, long id, String name, String uri) {
        this.database = database;
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String objectUri() {
        return uri;
    }

    @Override
    public AttachedTags tags() {
        return new DatabaseAttachedTags(database, id);
    }
}
