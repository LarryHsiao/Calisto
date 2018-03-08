package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.CalistoFile
import com.silverhetch.calisto.tagging.AttachedTag
import com.sun.javafx.collections.ObservableListWrapper
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class AttachedTagList : Initializable {
    @FXML
    private var attachedTagList: ListView<AttachedTag>? = null
    @FXML
    private var calistoFile: CalistoFile? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        attachedTagList!!.items = ObservableListWrapper<AttachedTag>(ArrayList<AttachedTag>())
        attachedTagList!!.setCellFactory {
            object : ListCell<AttachedTag>() {
                override fun updateItem(item: AttachedTag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) "" else item?.tag()!!.name()
                }
            }
        }
    }

    fun setup(calistoFile: CalistoFile) {
        this.calistoFile = calistoFile
        attachedTagList!!.items.addAll(calistoFile.attachedTags().all())
    }
}