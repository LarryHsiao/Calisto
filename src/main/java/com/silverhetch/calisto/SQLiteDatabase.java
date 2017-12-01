package com.silverhetch.calisto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase implements Database {
    public Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db.db");
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS object (" +
                        "  id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "  name TEXT NOT NULL," +
                        "  uri  TEXT NOT NULL" +
                        ");");
        return connection;
    }
}
