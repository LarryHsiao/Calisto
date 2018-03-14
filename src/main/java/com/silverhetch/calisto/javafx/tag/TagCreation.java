package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TagCreation implements Initializable {
    public interface TagCreateListener {
        void onTagCreated(Tag tag);
    }

    @FXML private TextField tagName;
    @FXML private Button confirm;

    private final Tags calistoTags;
    private TagCreateListener listener;

    public TagCreation() {
        this.listener = null;
        this.calistoTags = new CalistoFactory().tags();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setListener(TagCreateListener listener) {
        this.listener = listener;
    }

    public void onConfirmClick(MouseEvent mouseEvent) {
        String inputName = tagName.getText();
        if (inputName == null || inputName.isEmpty()) {
            return;
        }
        Tag createdTag = calistoTags.addTag(inputName, "");
        tagName.setText("");

        if (listener != null) {
            listener.onTagCreated(createdTag);
        }
    }
}
