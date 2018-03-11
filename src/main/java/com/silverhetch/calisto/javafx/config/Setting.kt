package com.silverhetch.calisto.javafx.config

import com.silverhetch.calisto.config.ConfigurationFactory
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

class Setting : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        ConfigurationFactory().config()
    }


}