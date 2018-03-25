package com.silverhetch.calisto.javafx.tag.selection;

import com.jfoenix.controls.JFXChipView
import com.silverhetch.calisto.CalistoFactory
import com.silverhetch.calisto.tagging.Tag
import com.silverhetch.calisto.tagging.Tags
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

class TagSelection : Initializable {
    @FXML
    private var clipView: JFXChipView<Tag>? = null
    private val tags: Tags = CalistoFactory().tags()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        clipView!!.converter = TagsStringConverter(tags)
    }
}
