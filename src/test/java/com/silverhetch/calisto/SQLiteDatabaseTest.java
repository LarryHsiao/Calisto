package com.silverhetch.calisto;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteDatabaseTest {
    @Test
    void initialSQLite() throws SQLException {
        new SQLiteDatabase().connection();
    }
}