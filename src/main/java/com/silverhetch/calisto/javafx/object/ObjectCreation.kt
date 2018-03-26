package com.silverhetch.calisto.javafx.`object`

import com.jfoenix.controls.events.JFXAutoCompleteEvent
import com.silverhetch.calisto.CalistoFactory
import com.silverhetch.calisto.CalistoFiles
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
    interface Callback {
        fun onConfirm()
    }

    @FXML
    private var tagSelectionController: TagSelection? = null
    @FXML
    private var fileList: ListView<File>? = null
    private var callback: Callback? = null
    private val calistoFiles: CalistoFiles = CalistoFactory().objects()
    private val data = ObservableListWrapper<File>(ArrayList<File>())
    private var resourceBundle: ResourceBundle? = null;

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        JFXAutoCompleteEvent.SELECTION // don`t know why, just for not crash
        resourceBundle = resources
        fileList!!.items = data
        fileList!!.cellFactory = Callback<ListView<File>, ListCell<File>> {
            object : ListCell<File>() {
                override fun updateItem(item: File?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) {
                        ""
                    } else {
                        FileNameIndicate(item!!, resourceBundle!!).value()
                    }
                }
            }
        }

    }

    fun setup(files: Array<File>, callback: Callback) {
        this.callback = callback;
        data.setAll(*files)
    }

    fun onConfirm() {
        data.forEach { file -> calistoFiles.put(file,*tagSelectionController!!.tags()) }
        callback!!.onConfirm()
    }
}