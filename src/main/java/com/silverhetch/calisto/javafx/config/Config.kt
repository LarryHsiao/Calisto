package com.silverhetch.calisto.javafx.config

import com.silverhetch.calisto.config.Configuration
import com.silverhetch.calisto.config.ConfigurationFactory
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.stage.DirectoryChooser
import java.net.URL
import java.util.*

class Config : Initializable {
    private var resources: ResourceBundle? = null;
    private val config: Configuration
    @FXML
    private var homePathField: TextField? = null;

    init {
        config = ConfigurationFactory().config()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.resources = resources
        updateHomePath()
    }

    fun showHomeChooser(event: ActionEvent) {
        val chooser = DirectoryChooser()
        chooser.title = resources!!.getString("setting.homeChoose")
        chooser.initialDirectory = config.workspaceFile()
        val newHome = chooser.showDialog((event.source as Node).scene.window) ?: return
        config.changeHome(newHome.absolutePath)
        updateHomePath()
    }

    fun updateHomePath() {
        homePathField!!.text = config.workspaceFile().absolutePath
    }
}