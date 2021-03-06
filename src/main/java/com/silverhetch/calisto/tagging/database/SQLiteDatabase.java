package com.silverhetch.calisto.tagging.database;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class SQLiteDatabase implements Database {
    private final Configuration config;

    SQLiteDatabase(Configuration config) {
        this.config = config;
    }

    @Override
    public Connection connection() throws SQLException {
        final File dbFile = new File(config.workspaceFile(), "db.db");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS object (" +
                            "  id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "  name TEXT NOT NULL," +
                            "  uri  TEXT NOT NULL" +
                            ");");
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS tag ( " +
                            " id        INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " name      TEXT NOT NULL," +
                            " uri_image TEXT NOT NULL, " +
                            " UNIQUE (name) "+
                            ");");
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS object_tag (" +
                            " id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                            " object_id INTEGER, " +
                            " tag_id INTEGER, " +
                            " UNIQUE (object_id, tag_id)"+
                            ");");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public void clear() {
        try (Connection connection = connection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("DROP TABLE IF EXISTS object");
            statement.executeUpdate("DROP TABLE IF EXISTS object_tag");
            statement.executeUpdate("DROP TABLE IF EXISTS tag");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
