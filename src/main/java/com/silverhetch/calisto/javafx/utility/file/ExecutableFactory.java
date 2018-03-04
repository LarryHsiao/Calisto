package com.silverhetch.calisto.javafx.utility.file;

public class ExecutableFactory {
    public Executable file(){
        return new FileExecutable();
    }

    public Executable directory(){
        return new DirectoryExecutable();
    }
}
