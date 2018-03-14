package com.silverhetch.calisto.javafx.component

import com.silverhetch.calisto.config.ConfigurationFactory
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.stage.FileChooser
import java.io.File
import java.net.URL
import java.util.*

class ImageChoose : Initializable {
    @FXML
    private var imageUriField: TextField? = null
    private val config = ConfigurationFactory().config()
    private var resources: ResourceBundle? = null
    private var imageFile: File? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
    }

    fun chooseImage(e: MouseEvent) {
        val chooser = FileChooser()
        chooser.title = resources!!.getString("imageChoose.title")
        chooser.initialDirectory = config.workspaceFile()
        chooser.extensionFilters.addAll(FileChooser.ExtensionFilter("images", "*.png", "*.jpg", "*.jpeg"))
        imageFile = chooser.showOpenDialog((e.source as Node).scene.window)
        imageUriField!!.text = imageFile!!.absolutePath
    }

    fun imageUri() = imageFile!!.toURI().toString()

    fun cleanField() {
        imageUriField!!.text = ""
    }
}