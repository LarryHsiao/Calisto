package com.silverhetch.calisto.tagging;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.calisto.tagging.database.DatabaseFactory;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DatabaseTagsTest {
    private final Configuration configuration = new MockConfiguration();
    @Before
    public void setUp() throws Exception {
        new DatabaseFactory(configuration).database().clear();
    }

    @Test
    public void insert_checkContent() {
        Tags tags = new DatabaseTags(new DatabaseFactory(configuration).database());
        tags.addTag("tag01", "uri01");
        Tag inserted = tags.all()[0];
        assertEquals(1, inserted.id());
        assertEquals("tag01", inserted.name());
        assertEquals("uri01", inserted.imageUri());
    }

    @Test
    public void deleteById_checkCount() {
        Tags tags = new DatabaseTags(new DatabaseFactory(configuration).database());
        Tag tagInserted = tags.addTag("tag02", "uri02");
        tagInserted.delete();

        assertEquals(0, tags.all().length);
    }
}