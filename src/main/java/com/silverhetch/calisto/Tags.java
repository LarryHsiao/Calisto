package com.silverhetch.calisto;

public interface Tags {
    Tag[] all();
    Tag insertTag(String name, String uri);
    void deleteById();
}
