package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.javafx.tag.selection.TagSelection
import com.sun.javafx.collections.ObservableListWrapper
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class ObjectCreation : Initializable {
    @FXML
    private var tagSelectionController: TagSelection? = null
    @FXML
    private var fileList: ListView<File>? = null
    private val data = ObservableListWrapper<File>(ArrayList<File>())

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        fileList!!.items = data
        fileList!!.cellFactory = Callback<ListView<File>, ListCell<File>> {
            object : ListCell<File>() {
                override fun updateItem(item: File?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) {
                        ""
                    } else {
                        item!!.name
                    }
                }
            }
        }
    }

    fun setup(files: Array<File>) {
        data.setAll(*files)
    }
}