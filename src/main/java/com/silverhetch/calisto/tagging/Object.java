package com.silverhetch.calisto.tagging;

public interface Object {
    long id();

    String name();

    String objectUri();

    AttachedTags tags();

    void delete();
}
