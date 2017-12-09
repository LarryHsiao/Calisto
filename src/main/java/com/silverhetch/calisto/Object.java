package com.silverhetch.calisto;

public interface Object {
    long id();
    String name();
    String objectUri();
    AttachedTags tags();
    void delete();
}
