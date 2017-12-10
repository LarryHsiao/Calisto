package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DatabaseAttachedTag implements AttachedTag {
    private final Database database;
    private final Tag tag;
    private final long objectId;

    DatabaseAttachedTag(Database database, Tag tag, long objectId) {
        this.database = database;
        this.tag = tag;
        this.objectId = objectId;
    }


    @Override
    public Tag tag() {
        return tag;
    }

    @Override
    public void delete() {
        try (Connection connection = database.connection();
             PreparedStatement deleteLink = connection.prepareStatement("DELETE FROM object_tag WHERE object_id=? AND tag_id=?")
        ) {
            deleteLink.setLong(1, objectId);
            deleteLink.setLong(2, tag.id());
            if (deleteLink.executeUpdate() != 1) {
                throw new RuntimeException("delete tag on object failed tagId:" + tag.id() + " objectId:" + objectId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
