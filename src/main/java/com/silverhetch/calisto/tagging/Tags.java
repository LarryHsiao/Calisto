package com.silverhetch.calisto.tagging;

public interface Tags {
    Tag[] all();

    Tag addTag(String name, String uri);

}
