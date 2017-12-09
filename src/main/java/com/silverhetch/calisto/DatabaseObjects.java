package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;

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
    public Object insert(String name, String uri) {
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

    @Override
    public void deleteById(long objectId) {
        try (Connection connection = database.connection();
             PreparedStatement deleteObject = connection.prepareStatement("DELETE FROM object WHERE id=?");
             PreparedStatement deleteLinks = connection.prepareStatement("DELETE FROM object_tag WHERE object_id=?")
        ) {
            deleteLinks.setLong(1, objectId);
            deleteLinks.executeUpdate();

            deleteObject.setLong(1, objectId);
            if (deleteObject.executeUpdate() != 1) {
                throw new RuntimeException("delete object failed: "+ objectId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
