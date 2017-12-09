package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @Override
    public void delete() {
        try (Connection connection = database.connection();
             PreparedStatement deleteTag = connection.prepareStatement("DELETE FROM tag WHERE id=?");
             PreparedStatement deleteLink = connection.prepareStatement("DELETE FROM object_tag WHERE tag_id=?")
        ) {
            connection.setAutoCommit(false);
            deleteLink.setLong(1, id);
            deleteLink.executeUpdate();

            deleteTag.setLong(1, id);
            final boolean tagDeleteSuccess = deleteTag.executeUpdate() == 1;
            if (!tagDeleteSuccess) {
                connection.rollback();
                throw new RuntimeException("delete tag failed id: " + id);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
