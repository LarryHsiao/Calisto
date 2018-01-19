package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

class StorageImpl implements Storage {
    private final File root;

    StorageImpl(Configuration configuration) {
        this.root = new File(configuration.workspaceFile(), "storage");
    }

    @Override
    public StorageFile save(File file) throws Exception {
        File parent = availableParent();
        move(file, new File(parent, file.getName()));
        return new StorageFileImpl(parent);
    }

    private void move(File target, File dest) throws IOException {
        if (target.isDirectory()) {
            for (File file : target.listFiles()) {
                move(file, new File(dest, file.getName()));
            }
        } else {
            dest.getParentFile().mkdirs();
            Files.move(target.toPath(), dest.toPath());
        }
    }

    @Override
    public StorageFile get(String id) {
        final File file = new File(root, id);
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }
        return new StorageFileImpl(file);
    }

    @Override
    public String path() {
        return root.getAbsolutePath();
    }

    private File availableParent() {
        File file;
        do {
            file = new File(root, UUID.randomUUID().toString());
        } while (file.exists());

        if (!file.mkdirs()) {
            throw new RuntimeException("File root not accessible");
        }
        return file;
    }
}
