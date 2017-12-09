package com.silverhetch.calisto;

import com.silverhetch.calisto.database.Database;
import com.silverhetch.calisto.database.DatabaseFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class SQLiteDatabaseTest {
    @Test
    void initialSQLite() throws SQLException {
        new DatabaseFactory().database().connection();
        assertTrue(new File("db.db").exists());
    }

    @Test
    void clearDatabase() throws Exception {
        final Database database = new DatabaseFactory().database();
        try (Connection connection = database.connection()) {
            database.clear();
            try (ResultSet resultSet = connection.getMetaData().getTables(null, null, "%", null)) {
                while (resultSet.next()) {
                    final String tableName = resultSet.getString(resultSet.findColumn("TABLE_NAME"));
                    fail("the table not deleted: " + tableName);
                }
            }
        }
        assertTrue(true);
    }
}