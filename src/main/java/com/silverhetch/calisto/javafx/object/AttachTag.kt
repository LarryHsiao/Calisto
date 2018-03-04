package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.CalistoFactory
import com.silverhetch.calisto.CalistoObject
import com.silverhetch.calisto.CalistoTag
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

class AttachTag(val calistoObject: CalistoObject) : Initializable {
    @FXML
    private var tagList: ListView<CalistoTag>? = null
    @FXML
    private var confrimBUtton: Button? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val list = ArrayList<CalistoTag>()
        list.addAll(CalistoFactory().tags().all())
        tagList!!.items = ObservableListWrapper<CalistoTag>(list)
        tagList!!.setCellFactory {
            object : ListCell<CalistoTag>() {
                override fun updateItem(item: CalistoTag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) "" else item?.name()
                }
            }
        }
    }

    fun onConfirm(event: MouseEvent) {
        
    }
}