package com.silverhetch.calisto;

import java.io.IOException;

public interface CalistoObject {
    String name();

    CalistoObject[] subFiles();

    void attachTag(CalistoTag tag);

    void execute() throws IOException;
}
