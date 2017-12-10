package com.silverhetch.calisto.tagging;

public interface Tag {
    long id();

    String name();

    String imageUri();

    void delete();
}
