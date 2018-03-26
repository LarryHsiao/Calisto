package com.silverhetch.calisto.javafx.tag.selection;

import com.jfoenix.controls.JFXChipView
import com.jfoenix.controls.events.JFXAutoCompleteEvent
import com.silverhetch.calisto.CalistoFactory
import com.silverhetch.calisto.tagging.Tag
import com.silverhetch.calisto.tagging.Tags
import javafx.event.Event
import javafx.event.EventDispatcher
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListCell
import java.net.URL
import java.util.*

class TagSelection : Initializable {
    @FXML
    private var chipView: JFXChipView<Tag>? = null
    private val tags: Tags = CalistoFactory().tags()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        chipView!!.converter = TagsStringConverter(tags)
        chipView!!.suggestions.addAll(tags.all())
        chipView!!.setSuggestionsCellFactory {
            object : ListCell<Tag>() {
                override fun updateItem(item: Tag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) {
                        ""
                    } else {
                        item!!.name()
                    }
                }
            }
        }
    }

    fun tags(): Array<Tag> = chipView!!.chips.toTypedArray()
}
