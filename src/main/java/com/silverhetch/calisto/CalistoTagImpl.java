package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;

import java.io.File;
import java.net.URI;

 class CalistoTagImpl implements CalistoTag {
    private final Tag tag;

     CalistoTagImpl(Tag tag) {
        this.tag = tag;
    }

     @Override
     public long id() {
         return tag.id();
     }

     @Override
    public String name() {
        return tag.name();
    }

     @Override
    public URI imageUri() {
        return URI.create(tag.imageUri());
    }

    @Override
    public void delete(){
        tag.delete();
        new File(imageUri().getPath()).delete();
    }
}
