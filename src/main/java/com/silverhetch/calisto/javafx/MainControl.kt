package com.silverhetch.calisto.javafx

import com.silverhetch.calisto.javafx.`object`.DragToInsert
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.URL
import java.util.*

class MainControl : Initializable {
    @FXML
    private var dragToInsertController: DragToInsert? = null
    private var resources: ResourceBundle? = null;

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources;
    }

    fun tagManagement(actionEvent: ActionEvent) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/TagManagement.fxml"), resources)
        val dialog = Stage()
        dialog.scene = Scene(root)
        dialog.showAndWait()
    }
}