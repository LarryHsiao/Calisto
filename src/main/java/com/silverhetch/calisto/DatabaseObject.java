package com.silverhetch.calisto;

class DatabaseObject implements Object {
    private final Database database;
    private final int id;
    private final String name;
    private final String uri;

    public DatabaseObject(Database database, int id, String name, String uri) {
        this.database = database;
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    @Override
    public int id() {
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
    public Tags tags() {
        return new SingleObjectTags(database, id);
    }
}
