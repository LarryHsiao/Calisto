package com.silverhetch.calisto.javafx

import com.silverhetch.calisto.javafx.`object`.DragToInsert
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

class MainControl : Initializable {
    @FXML
    var dragToInsertController: DragToInsert? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }
}