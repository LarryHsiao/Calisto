package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseObjects implements Objects {
    private final Database database;

    DatabaseObjects(Database database) {
        this.database = database;
    }

    @Override
    public Object[] all() {
        try (Connection connection = database.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM object")
        ) {
            List<Object> resultList = new ArrayList<>();
            while (resultSet.next()) {
                resultList.add(new DatabaseObject(
                        database,
                        resultSet.getInt(resultSet.findColumn("id")),
                        resultSet.getString(resultSet.findColumn("name")),
                        resultSet.getString("uri")));
            }
            return resultList.toArray(new Object[resultSet.getFetchSize()]);
        } catch (SQLException e) {
            return new Object[0];
        }
    }

    @Override
    public Object add(String name, String uri) {
        try (Connection connection = database.connection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO object (name, uri) VALUES (?, ?);");
        ) {
            statement.setString(1, name);
            statement.setString(2, uri);
            final boolean success = statement.executeUpdate() == 1;
            if (!success) {
                throw new RuntimeException("insert object failed");
            }
            final long objectId = obtainId(statement);
            return new DatabaseObject(database, objectId, name, uri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long obtainId(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }
}
