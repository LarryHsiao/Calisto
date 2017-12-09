package com.silverhetch.calisto;

import com.silverhetch.calisto.database.DatabaseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTagsTest {
    @BeforeEach
    void setUp() {
        new DatabaseFactory().database().clear();
    }

    @Test
    void insert_checkContent() {
        Tags tags = new DatabaseTags(new DatabaseFactory().database());
        tags.insertTag("tag01", "uri01");
        Tag inserted = tags.all()[0];
        assertEquals(1, inserted.id());
        assertEquals("tag01", inserted.name());
        assertEquals("uri01", inserted.imageUri());
    }

    @Test
    void deleteById_checkCount() {
        Tags tags = new DatabaseTags(new DatabaseFactory().database());
        Tag tagInserted = tags.insertTag("tag02", "uri02");
        tags.deleteById(tagInserted.id());

        assertEquals(0, tags.all().length);
    }
}