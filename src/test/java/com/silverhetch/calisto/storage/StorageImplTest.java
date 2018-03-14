package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;
import com.silverhetch.mock.MockConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class StorageImplTest {
    private final Configuration configuration = new MockConfiguration();

    @Test
    public void saveFile_checkOriginalFileExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        storage.save(file);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void saveFile_checkFileExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        StorageFile fileSaved = storage.save(file);
        Assert.assertTrue(new File(fileSaved.uri()).exists());
    }

    @Test
    public void saveDiectory_checkFileExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget/temp");
        file.mkdirs();
        file.createNewFile();
        StorageFile fileSaved = storage.save(file);
        Assert.assertTrue(new File(fileSaved.uri()).exists());
    }

    @Test
    public void get_checkExistWithId() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        StorageFile fileSaved = storage.save(file);
        StorageFile fileFromGet = storage.get(fileSaved.id());
        Assert.assertTrue(new File(fileSaved.uri()).exists());
    }

    @Test
    public void deleteWithDirectory_checkNotExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget/temp");
        file.mkdirs();
        file.createNewFile();
        StorageFile fileSaved = storage.save(file);
        fileSaved.delete();
        Assert.assertFalse(new File(fileSaved.uri()).exists());
    }

    @Test
    public void delete_checkNotExist() throws Exception {
        StorageFactory factory = new StorageFactory(configuration);
        Storage storage = factory.storage();
        final File file = new File(configuration.workspaceFile(), "tempTarget");
        file.mkdirs();
        file.createNewFile();
        StorageFile fileSaved = storage.save(file);
        fileSaved.delete();
        Assert.assertFalse(new File(fileSaved.uri()).exists());
    }
}