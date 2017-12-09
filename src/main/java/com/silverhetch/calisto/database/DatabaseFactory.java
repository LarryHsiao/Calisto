package com.silverhetch.calisto.database;

public class DatabaseFactory {
    public Database database(){
        return new SQLiteDatabase();
    }
}
