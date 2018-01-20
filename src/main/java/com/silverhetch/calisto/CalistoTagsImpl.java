package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;

class CalistoTagsImpl implements CalistoTags {
    private final Tags tags;

    CalistoTagsImpl(Tags tag) {
        this.tags = tag;
    }

    @Override
    public CalistoTag[] all() {
        Tag[] tagArray = tags.all();
        CalistoTag[] outputArray = new CalistoTag[tagArray.length];
        for (int i = 0; i < tagArray.length; i++) {
            outputArray[i] = new CalistoTagImpl(tagArray[i]);
        }
        return outputArray;
    }

    @Override public CalistoTag create(String name) {
        return new CalistoTagImpl(tags.addTag(name, ""));
    }
}
