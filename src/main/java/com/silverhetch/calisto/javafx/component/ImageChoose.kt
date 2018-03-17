package com.silverhetch.calisto.javafx.component

import com.silverhetch.calisto.config.ConfigurationFactory
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.stage.FileChooser
import java.net.URL
import java.util.*

class ImageChoose : Initializable {
    @FXML
    private var imageUriField: TextField? = null
    private val config = ConfigurationFactory().config()
    private var resources: ResourceBundle? = null
    private var selectUri: String = ""

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
        imageUriField!!.textProperty().addListener(
                { observable, oldValue, newValue -> selectUri = newValue }
        )
    }

    fun chooseImage(e: MouseEvent) {
        val chooser = FileChooser()
        chooser.title = resources!!.getString("imageChoose.title")
        chooser.initialDirectory = config.workspaceFile()
        chooser.extensionFilters.addAll(FileChooser.ExtensionFilter("images", "*.png", "*.jpg", "*.jpeg"))
        val imageFile = chooser.showOpenDialog((e.source as Node).scene.window)
        imageUriField!!.text = imageFile!!.absolutePath
        selectUri = imageFile.toURI().toString()
    }

    fun imageUri() = selectUri

    fun cleanField() {
        imageUriField!!.text = ""
    }
}