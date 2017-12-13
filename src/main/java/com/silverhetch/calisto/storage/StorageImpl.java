package com.silverhetch.calisto.storage;

import com.silverhetch.calisto.config.Configuration;

import java.io.File;
import java.util.UUID;

class StorageImpl implements Storage {
    private final File root;

    StorageImpl(Configuration configuration) {
        this.root = new File(configuration.workspaceFile(), "storage");
    }

    @Override
    public CalistoFiles save(File file) {
        File parent = availableParent();
        if (!file.renameTo(new File(parent, file.getName()))) {
            throw new RuntimeException("move file failed.");
        }
        return new CalistoFilesImpl(parent);
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
