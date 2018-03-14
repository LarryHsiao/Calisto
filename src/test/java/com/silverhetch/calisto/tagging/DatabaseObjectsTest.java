package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DatabaseObjectsTest {
    private final Configuration configuration = new MockConfiguration();
    @Before
    public void setUp() {
        new DatabaseFactory(configuration).database().clear();
    }

    @Test
    public void insertObject_checkContent() {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory(configuration).database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        assertEquals(1, objectInserted.id());
        assertEquals("name001", objectInserted.name());
        assertEquals("uri", objectInserted.objectUri());
    }

    @Test
    public void deleteObject_checkCount() {
        DatabaseObjects objects = new DatabaseObjects(new DatabaseFactory(configuration).database());
        objects.add("name001", "uri");
        Object objectInserted = objects.all()[0];
        objectInserted.delete();
        assertEquals(0, objects.all().length);
    }
}