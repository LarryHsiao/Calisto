package com.silverhetch.calisto.javafx.tag

import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

class TagManagement : Initializable {
    @FXML
    private var tagCreationController: TagCreation? = null
    @FXML
    private var tagListController: TagList? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        tagCreationController!!.setListener { tagListController!!.loadList() }
    }
}