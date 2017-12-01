package com.silverhetch.calisto;

interface Schema {
    String SQL_CREATE_TABLE_TAG =
            "CREATE TABLE IF NOT EXISTS tag\n" +
                    "(\n" +
                    "  id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  name      TEXT NOT NULL,\n" +
                    "  uri_image TEXT NOT NULL\n" +
                    ");";


    String SQL_CREATE_TABLE_OBJECT_TAG =
            "CREATE TABLE IF NOT EXISTS object_tag (\n" +
                    "  id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  object_id INTEGER,\n" +
                    "  tag_id    INTEGER\n" +
                    "\n" +
                    ");";
}
