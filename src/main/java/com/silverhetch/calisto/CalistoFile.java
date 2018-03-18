package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.AttachedTags;

import java.io.IOException;

public interface CalistoFile {
    String name();

    CalistoFile[] subFiles();

    AttachedTags attachedTags();

    void delete();

    void execute() throws IOException;
}
