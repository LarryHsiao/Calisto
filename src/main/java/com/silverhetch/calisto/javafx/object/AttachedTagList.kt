package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.CalistoFile
import com.silverhetch.calisto.javafx.component.ListImageViewFactory
import com.silverhetch.calisto.tagging.AttachedTag
import com.sun.javafx.collections.ObservableListWrapper
import javafx.collections.ObservableList
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
    private val data: ObservableList<AttachedTag>;

    init {
        data = ObservableListWrapper<AttachedTag>(ArrayList<AttachedTag>())
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        attachedTagList!!.items = data
        attachedTagList!!.setCellFactory {
            object : ListCell<AttachedTag>() {
                override fun updateItem(item: AttachedTag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    if (empty) {
                        text = ""
                        graphic = null
                    } else {
                        text = item?.tag()!!.name()
                        graphic = ListImageViewFactory(item.tag().imageUri()).imageView()
                    }

                }
            }
        }
    }

    fun setup(calistoFile: CalistoFile) {
        this.calistoFile = calistoFile
        loadList()
    }

    fun loadList() {
        data.clear()
        data.addAll(calistoFile!!.attachedTags().all())
    }
}