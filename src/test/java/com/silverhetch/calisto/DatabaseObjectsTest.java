package com.silverhetch.calisto;

import com.silverhetch.calisto.database.DatabaseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseObjectsTest {
    @BeforeEach
    void setUp() throws Exception {
        new DatabaseFactory().database().clear();
    }

    @Test
    void insertObject_checkContent() throws Exception {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory().database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        assertEquals(1, objectInserted.id());
        assertEquals("name001", objectInserted.name());
        assertEquals("uri", objectInserted.objectUri());
    }

    @Test
    void deleteObject_checkCount() {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory().database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        objectInserted.delete();
        assertEquals(0, objects.all().length);
    }
}