package com.silverhetch.calisto.javafx.`object`

import com.silverhetch.calisto.CalistoFile
import com.silverhetch.calisto.javafx.tag.TagList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

class AttachTag : Initializable {
    @FXML
    private var tagListController: TagList? = null
    @FXML
    private var attachedTagListController: AttachedTagList? = null
    @FXML
    private var calistoFile: CalistoFile? = null;

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    fun setup(calistoFile: CalistoFile) {
        this.calistoFile = calistoFile
        attachedTagListController!!.setup(calistoFile)
    }
}