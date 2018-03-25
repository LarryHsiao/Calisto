package com.silverhetch.calisto.javafx;

import java.util.ResourceBundle;

public class ResourceBundleFactory {
    public ResourceBundle instance(){
        return ResourceBundle.getBundle("i18n/bundle");
    }
}
