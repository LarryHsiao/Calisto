package com.silverhetch.calisto.tagging.database;

public class DatabaseFactory {
    public Database database() {
        return new SQLiteDatabase();
    }
}
