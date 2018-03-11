package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TagList implements Initializable {
    @FXML private ListView<Tag> tagList;
    private ResourceBundle resourceBundle;
    private final Tags tags;

    public TagList() {
        this.tags = new CalistoFactory().tags();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        tagList.setItems(new ObservableListWrapper<>(Arrays.asList(tags.all())));
        tagList.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>() {
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.name());
                    }
                };
            }
        });
    }

    public Tag selectedTag() {
        return tagList.getSelectionModel().getSelectedItems().get(0);
    }
}
