package com.silverhetch.calisto.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    Connection connection() throws SQLException;
    void clear ();
}
