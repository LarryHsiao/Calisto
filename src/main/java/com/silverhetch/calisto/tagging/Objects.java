package com.silverhetch.calisto.tagging;

public interface Objects {
    Object[] all();

    Object[] byTagName(String tagName);

    Object add(String name, String uri);
}
