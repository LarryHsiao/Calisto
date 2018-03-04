package com.silverhetch.calisto.javafx.utility.file;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;

public interface Executable {
    void execute(@NotNull URI uri) throws IOException;
}
