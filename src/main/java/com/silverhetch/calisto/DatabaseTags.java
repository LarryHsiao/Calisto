package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseTags implements Tags {
    private final Database database;

    public DatabaseTags(Database database) {
        this.database = database;
    }

    @Override
    public Tag[] all() {
        try (Connection connection = database.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tag")
        ) {
            List<Tag> tagList = new ArrayList<>();
            while (resultSet.next()) {
                tagList.add(new DatabaseTag(
                        database,
                        resultSet.getLong(resultSet.findColumn("id")),
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
    public Tag addTag(String name, String uri) {
        try (Connection connection = database.connection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO tag (name, uri_image) VALUES (?, ?);");
        ) {
            statement.setString(1, name);
            statement.setString(2, uri);
            final boolean success = statement.executeUpdate() == 1;
            if (!success) {
                throw new RuntimeException("insert tag failed: " + name);
            }
            final long tagId = obtainId(statement);
            return new DatabaseTag(database, tagId, name, uri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long obtainId(Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }
}
