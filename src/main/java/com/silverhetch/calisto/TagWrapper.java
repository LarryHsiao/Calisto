package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;

class TagWrapper implements Tag {
    private final CalistoTag calistoTag;

    TagWrapper(CalistoTag calistoTag) {
        this.calistoTag = calistoTag;
    }

    @Override
    public long id() {
        return calistoTag.id();
    }

    @Override
    public String name() {
        return calistoTag.name();
    }

    @Override
    public String imageUri() {
        return calistoTag.imageUri().toString();
    }

    @Override
    public void delete() {
        calistoTag.delete();
    }
}
