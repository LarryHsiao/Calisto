package com.silverhetch.calisto.javafx.tag.selection;

import com.silverhetch.calisto.tagging.Tag
import com.silverhetch.calisto.tagging.Tags
import javafx.util.StringConverter

internal class TagsStringConverter(private val tags: Tags) : StringConverter<Tag>() {
    override fun toString(`object`: Tag?): String {
        return `object`!!.name()
    }

    override fun fromString(string: String?): Tag {
        for (tag in tags.all()) {
            if (tag.name().equals(string)) {
                return tag
            }
        }
        return tags.addTag(string, "");
    }

}
