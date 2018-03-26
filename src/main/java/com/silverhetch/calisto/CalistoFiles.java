package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;

import java.io.File;

public interface CalistoFiles {
    CalistoFile put(File file, String... tags) throws Exception;
    CalistoFile put(File file, Tag...tags) throws Exception;

    CalistoFile[] byTag(String tagName);

    CalistoFile[] all();
}
