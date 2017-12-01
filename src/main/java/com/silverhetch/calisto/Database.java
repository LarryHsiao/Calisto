package com.silverhetch.calisto;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    Connection connection() throws SQLException;
}
