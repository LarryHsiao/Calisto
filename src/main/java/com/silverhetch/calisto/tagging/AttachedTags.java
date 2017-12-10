package com.silverhetch.calisto.tagging;

public interface AttachedTags {
    AttachedTag[] all();

    AttachedTag addTag(String name, String uri);
}
