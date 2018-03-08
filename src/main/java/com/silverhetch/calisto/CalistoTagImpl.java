package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;

import java.io.File;

class CalistoTagImpl implements Tag {
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
    public String imageUri() {
        return tag.imageUri();
    }

    @Override
    public void delete() {
        tag.delete();
        new File(imageUri()).delete();
    }
}
