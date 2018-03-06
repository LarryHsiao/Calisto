package com.silverhetch.calisto.tagging;

public interface AttachedTags {
    AttachedTag[] all();
    AttachedTag addTag(String name, String imageUri);
    AttachedTag addTag(Tag tag);
}
