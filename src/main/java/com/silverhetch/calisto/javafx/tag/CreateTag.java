package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoTag;
import com.silverhetch.calisto.CalistoTags;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTag implements Initializable {
    public interface TagCreateListener {
        void onTagCreated(CalistoTag tag);
    }

    @FXML private TextField tagName;
    @FXML private Button confirm;

    private final CalistoTags calistoTags;
    private TagCreateListener listener;

    public CreateTag() {
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
        CalistoTag createdTag = calistoTags.create(inputName);
        tagName.setText("");

        if (listener != null) {
            listener.onTagCreated(createdTag);
        }
    }
}
