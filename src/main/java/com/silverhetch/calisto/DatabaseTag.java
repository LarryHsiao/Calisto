package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

class DatabaseTag implements Tag {
    private final Database database;
    private final long id;
    private final String name;
    private final String imageUri;

    DatabaseTag(Database database, long id, String name, String imageUri) {
        this.database = database;
        this.id = id;
        this.name = name;
        this.imageUri = imageUri;
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
    public String imageUri() {
        return imageUri;
    }
}
