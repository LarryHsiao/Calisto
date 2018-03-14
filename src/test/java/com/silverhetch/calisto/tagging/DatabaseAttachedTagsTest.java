package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseAttachedTagsTest {
    private final Configuration configuration = new MockConfiguration();

    @Before
    public void setUp() {
        new DatabaseFactory(configuration).database().clear();
    }

    @Test
    public void insert_checkExist() {
        Objects Objects = new DatabaseObjects(new DatabaseFactory(configuration).database());
        Object object = Objects.add("object", "uri");
        object.tags().addTag("objectTag", "uriTag");
        Tag insertedTag = object.tags().all()[0].tag();
        assertEquals(1, insertedTag.id());
        assertEquals("objectTag", insertedTag.name());
        assertEquals("uriTag", insertedTag.imageUri());
    }

    @Test
    public void delete_checkCount() {
        Objects Objects = new DatabaseObjects(new DatabaseFactory(configuration).database());
        Object object = Objects.add("object", "uri");
        object.tags().addTag("objectTag", "uriTag");
        AttachedTag insertedTag = object.tags().all()[0];
        insertedTag.delete();
        assertEquals(0, object.tags().all().length);
    }
}