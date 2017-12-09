package com.silverhetch.calisto;

public interface Objects {
    Object[] all();

    Object add(String name, String uri);

    void deleteById(long objectId);

}
