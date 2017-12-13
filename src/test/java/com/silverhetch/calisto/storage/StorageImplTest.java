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
    public void saveFile_checkOriginalFileNotExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        storage.save(file);
        Assert.assertFalse(file.exists());
    }

    @Test
    public void saveFile_checkFileExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        CalistoFile fileSaved = storage.save(file);
        Assert.assertTrue(fileSaved.file().exists());
    }

    @Test
    public void saveDiectory_checkFileExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget/temp");
        file.mkdirs();
        file.createNewFile();
        CalistoFile fileSaved = storage.save(file);
        Assert.assertTrue(fileSaved.file().exists());

    }

    @Test
    public void get_checkExistWithId() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        CalistoFile fileSaved = storage.save(file);
        CalistoFile fileFromGet = storage.get(fileSaved.id());
        Assert.assertTrue(fileFromGet.file().exists());
    }

    @Test
    public void deleteWithDirectory_checkNotExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget/temp");
        file.mkdirs();
        file.createNewFile();
        CalistoFile fileSaved = storage.save(file);
        fileSaved.delete();
        Assert.assertFalse(fileSaved.file().exists());
    }

    @Test
    public void delete_checkNotExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        CalistoFile fileSaved = storage.save(file);
        fileSaved.delete();
        Assert.assertFalse(fileSaved.file().exists());
    }
}