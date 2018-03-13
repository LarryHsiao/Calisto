package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseAttachedTags implements AttachedTags {
    private final Database database;
    private final long objectId;

    DatabaseAttachedTags(Database database, long objectId) {
        this.database = database;
        this.objectId = objectId;
    }

    @Override
    public AttachedTag[] all() {
        try (Connection connection = database.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT tag.* FROM object_tag LEFT JOIN tag WHERE object_tag.object_id = "+objectId+" AND tag.id = object_tag.tag_id;");
        ) {
            List<AttachedTag> tagList = new ArrayList<>();
            while (resultSet.next()) {
                tagList.add(new DatabaseAttachedTag(
                        database,
                        new DatabaseTag(
                                database,
                                resultSet.getInt(resultSet.findColumn("id")),
                                resultSet.getString(resultSet.findColumn("name")),
                                resultSet.getString(resultSet.findColumn("uri_image"))
                        ), objectId));
            }
            return tagList.toArray(new AttachedTag[tagList.size()]);
        } catch (SQLException e) {
            return new AttachedTag[0];
        }
    }

    @Override
    public AttachedTag addTag(String name, String imageUri) {
        try (Connection connection = database.connection();
             PreparedStatement insertTagStatement = connection.prepareStatement("INSERT INTO tag (name, uri_image) VALUES (?, ?);");
             PreparedStatement linkToTagStatement = connection.prepareStatement("INSERT INTO object_tag (object_id, tag_id) VALUES (?, ?);");
        ) {
            connection.setAutoCommit(false);
            insertTagStatement.setString(1, name);
            insertTagStatement.setString(2, imageUri);
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
            return new DatabaseAttachedTag(database, new DatabaseTag(database, tagId, name, imageUri), objectId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AttachedTag addTag(Tag tag) {
        try (Connection connection = database.connection();
             PreparedStatement linkToTagStatement = connection.prepareStatement("INSERT INTO object_tag (object_id, tag_id) VALUES (?, ?);");
        ) {
            connection.setAutoCommit(false);
            linkToTagStatement.setLong(1, objectId);
            linkToTagStatement.setLong(2, tag.id());
            boolean linkSuccess = linkToTagStatement.executeUpdate() == 1;
            if (!linkSuccess) {
                connection.rollback();
                throw new RuntimeException("link exist tag failed, objectId:" + objectId);
            }
            connection.commit();
            return new DatabaseAttachedTag(database, tag, objectId);
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
}
