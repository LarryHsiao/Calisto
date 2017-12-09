package com.silverhetch.calisto;

public interface Tag {
    long id();
    String name();
    String imageUri();

    void delete();
}
