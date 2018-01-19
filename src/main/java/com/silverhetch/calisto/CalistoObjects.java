package com.silverhetch.calisto;

import java.io.File;

public interface CalistoObjects {
    CalistoObject put(File file, String... tags) throws Exception;

    CalistoObject[] byTag(String tagName);

    CalistoObject[] all();
}
