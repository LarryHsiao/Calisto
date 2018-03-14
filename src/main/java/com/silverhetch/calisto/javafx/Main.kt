package com.silverhetch.calisto.javafx

import com.silverhetch.calisto.javafx.`object`.ObjectList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*


class Main : Initializable {
    @FXML
    private var rootPane: BorderPane? = null
    @FXML
    private var objectListController: ObjectList? = null
    private var resources: ResourceBundle? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
        objectList()
    }

    fun objectList() {
        val tagListRoot = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/ObjectList.fxml"), resources)
        rootPane!!.center = tagListRoot
    }

    fun tagManagement() {
        val tagListRoot = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/TagManagement.fxml"), resources)
        rootPane!!.center = tagListRoot
    }

    fun options() {
        val optionRoot = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/Config.fxml"), resources)
        rootPane!!.center = optionRoot
    }
}