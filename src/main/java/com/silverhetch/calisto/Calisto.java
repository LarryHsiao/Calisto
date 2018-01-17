package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Object;

import java.io.File;

public interface Calisto {
    Object put(File file, String... tags);
}
