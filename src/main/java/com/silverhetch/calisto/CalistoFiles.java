package com.silverhetch.calisto;

import java.io.File;

public interface CalistoFiles {
    CalistoFile put(File file, String... tags) throws Exception;

    CalistoFile[] byTag(String tagName);

    CalistoFile[] all();
}
