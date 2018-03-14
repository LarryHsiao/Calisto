package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.config.ConfigurationFactory;
import com.silverhetch.calisto.tagging.database.Database;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SQLiteDatabaseTest {
    private final Configuration configuration = new MockConfiguration();

    @Test
    public void initialSQLite() throws SQLException {
        new DatabaseFactory(configuration).database().connection();
        assertTrue(new File(new ConfigurationFactory().config().workspaceFile(), "db.db").exists());
    }

    @Test
    public void clearDatabase() throws Exception {
        final Database database = new DatabaseFactory(configuration).database();
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