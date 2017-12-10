package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

    @Override
    public void delete() {
        try (Connection connection = database.connection();
             PreparedStatement deleteObject = connection.prepareStatement("DELETE FROM object WHERE id=?");
             PreparedStatement deleteLinks = connection.prepareStatement("DELETE FROM object_tag WHERE object_id=?")
        ) {
            deleteLinks.setLong(1, id);
            deleteLinks.executeUpdate();

            deleteObject.setLong(1, id);
            if (deleteObject.executeUpdate() != 1) {
                throw new RuntimeException("delete object failed: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
