package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.CalistoFile
import com.silverhetch.calisto.tagging.AttachedTag
import com.sun.javafx.collections.ObservableListWrapper
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.input.MouseEvent
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class AttachTag(val calistoFile: CalistoFile) : Initializable {
    @FXML
    private var attachedTagList: ListView<AttachedTag>? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val list = ArrayList<AttachedTag>()
        list.addAll(calistoFile.attachedTags().all());
        attachedTagList!!.items = ObservableListWrapper<AttachedTag>(list)
        attachedTagList!!.setCellFactory {
            object : ListCell<AttachedTag>() {
                override fun updateItem(item: AttachedTag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) "" else item?.tag()!!.name()
                }
            }
        }
    }
}