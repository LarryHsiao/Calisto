package com.silverhetch.calisto;

import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;

class CalistoTagsImpl implements Tags {
    private final Tags tags;

    CalistoTagsImpl(Tags tag) {
        this.tags = tag;
    }

    @Override
    public Tag[] all() {
        Tag[] tagArray = tags.all();
        for (int i = 0; i < tagArray.length; i++) {
            tagArray[i] = new CalistoTagImpl(tagArray[i]);
        }
        return tagArray;
    }

    @Override
    public Tag addTag(String name, String imageUri) {
        return new CalistoTagImpl(tags.addTag(name, ""));
    }
}
