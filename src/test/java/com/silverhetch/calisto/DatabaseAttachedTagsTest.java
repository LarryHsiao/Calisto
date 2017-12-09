package com.silverhetch.calisto;

import com.silverhetch.calisto.database.DatabaseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAttachedTagsTest {

    @BeforeEach
    void setUp() {
        new DatabaseFactory().database().clear();
    }

    @Test
    void insert_checkExist() {
        Objects Objects = new DatabaseObjects(new DatabaseFactory().database());
        Object object = Objects.add("object", "uri");
        object.tags().addTag("objectTag", "uriTag");
        Tag insertedTag = object.tags().all()[0].tag();
        assertEquals(1, insertedTag.id());
        assertEquals("objectTag", insertedTag.name());
        assertEquals("uriTag", insertedTag.imageUri());
    }

    @Test
    void delete_checkCount() {
        Objects Objects = new DatabaseObjects(new DatabaseFactory().database());
        Object object = Objects.add("object", "uri");
        object.tags().addTag("objectTag", "uriTag");
        AttachedTag insertedTag = object.tags().all()[0];
        insertedTag.delete();
        assertEquals(0, object.tags().all().length);
    }
}