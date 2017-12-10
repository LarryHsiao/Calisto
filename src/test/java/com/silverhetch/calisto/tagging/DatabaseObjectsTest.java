package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.tagging.database.DatabaseFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DatabaseObjectsTest {
    @Before
    public void setUp() {
        new DatabaseFactory().database().clear();
    }

    @Test
    public void insertObject_checkContent() {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory().database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        assertEquals(1, objectInserted.id());
        assertEquals("name001", objectInserted.name());
        assertEquals("uri", objectInserted.objectUri());
    }

    @Test
    public void deleteObject_checkCount() {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory().database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        objectInserted.delete();
        assertEquals(0, objects.all().length);
    }
}