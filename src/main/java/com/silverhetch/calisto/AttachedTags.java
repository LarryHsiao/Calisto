package com.silverhetch.calisto;

public interface AttachedTags {
    AttachedTag[] all();

    AttachedTag addTag(String name, String uri);
}
