package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class StorageImplTest {
    private final Configuration configuration = new MockConfiguration();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveFile_checkExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        storage.save(file);
        Assert.assertFalse(file.exists());
        Assert.assertTrue(new File(storage.path()).exists());
    }
}