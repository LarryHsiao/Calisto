package com.silverhetch.calisto;

class SingleObjectTags implements Tags {
    private final Database database;
    private final int objectId;

    SingleObjectTags(Database database, int objectId) {
        this.database = database;
        this.objectId = objectId;
    }

    @Override
    public Tag[] all() {
        return new Tag[0];
    }

    @Override
    public Tag insertTag(String name, String uri) {
        return null;
    }

    @Override
    public void deleteById() {

    }
}
