package com.silverhetch.calisto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DatabaseObjects implements Objects {
    private final Database database;

    public DatabaseObjects(Database database) {
        this.database = database;
    }

    @Override
    public Object[] all() {
        try (Connection connection = database.connection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM object")) {
                    List<Object> resultList = new ArrayList<>();
                    while (resultSet.next()) {
                        resultList.add(new DatabaseObject(
                                database,
                                resultSet.getInt(resultSet.findColumn("id")),
                                resultSet.getString(resultSet.findColumn("name")),
                                resultSet.getString("uri")));
                    }
                    return resultList.toArray(new Object[resultSet.getFetchSize()]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0];
        }
    }
}
