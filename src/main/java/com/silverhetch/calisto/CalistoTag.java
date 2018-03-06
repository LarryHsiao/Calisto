package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;

import java.net.URI;

public interface CalistoTag {
    long id();
    String name();
    URI imageUri();
    void delete();
}
