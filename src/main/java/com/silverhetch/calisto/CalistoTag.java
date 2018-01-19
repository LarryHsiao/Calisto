package com.silverhetch.calisto;

import java.net.URI;

public interface CalistoTag {
    String name();
    URI imageUri();

    void delete();
}
