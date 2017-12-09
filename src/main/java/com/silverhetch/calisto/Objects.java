package com.silverhetch.calisto;

public interface Objects {
    Object[] all();

    Object insert(String name, String uri);

    void deleteById(long objectId);

}
