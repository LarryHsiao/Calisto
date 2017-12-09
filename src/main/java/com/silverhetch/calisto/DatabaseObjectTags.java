package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseObjectTags implements Tags {
    private final Database database;
    private final long objectId;

    DatabaseObjectTags(Database database, long objectId) {
        this.database = database;
        this.objectId = objectId;
    }

    @Override
    public Tag[] all() {
        try (Connection connection = database.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT tag.* FROM object_tag LEFT JOIN tag WHERE object_tag.object_id = 1 AND tag.id = object_tag.tag_id;");
        ) {
            List<Tag> tagList = new ArrayList<>();
            while (resultSet.next()) {
                tagList.add(new DatabaseTag(
                        database,
                        resultSet.getInt(resultSet.findColumn("id")),
                        resultSet.getString(resultSet.findColumn("name")),
                        resultSet.getString(resultSet.findColumn("uri_image"))
                ));
            }
            return tagList.toArray(new Tag[tagList.size()]);
        } catch (SQLException e) {
            return new Tag[0];
        }
    }

    @Override
    public Tag insertTag(String name, String uri) {
        try (Connection connection = database.connection();
             PreparedStatement insertTagStatement = connection.prepareStatement("INSERT INTO tag (name, uri_image) VALUES (?, ?);");
             PreparedStatement linkToTagStatement = connection.prepareStatement("INSERT INTO object_tag (object_id, tag_id) VALUES (?, ?);");
        ) {
            connection.setAutoCommit(false);
            insertTagStatement.setString(1, name);
            insertTagStatement.setString(2, uri);
            boolean insertTagSuccess = insertTagStatement.executeUpdate() == 1;
            if (!insertTagSuccess) {
                connection.rollback();
                throw new RuntimeException("insert tag failed : " + name);
            }
            final long tagId = obtainId(insertTagStatement);
            linkToTagStatement.setLong(1, objectId);
            linkToTagStatement.setLong(2, tagId);
            boolean linkSuccess = linkToTagStatement.executeUpdate() == 1;
            if (!linkSuccess) {
                connection.rollback();
                throw new RuntimeException("link tag failed : " + name + ", objectId:" + objectId);
            }
            connection.commit();
            return new DatabaseTag(database, tagId, name, uri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long obtainId(PreparedStatement statement) throws SQLException {
        try (ResultSet insetResult = statement.getGeneratedKeys()) {
            insetResult.next();
            return insetResult.getLong(1);
        }
    }

    @Override
    public void deleteById(long tagId) {
        try (Connection connection = database.connection();
             PreparedStatement deleteLink = connection.prepareStatement("DELETE FROM object_tag WHERE object_id=? AND tag_id=?")
        ) {
            deleteLink.setLong(1, objectId);
            deleteLink.setLong(2, tagId);
            if (deleteLink.executeUpdate() != 1) {
                throw new RuntimeException("delete tag on object failed tagId:" + tagId + " objectId:" + objectId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
