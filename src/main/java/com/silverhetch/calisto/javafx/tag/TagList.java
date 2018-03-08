package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TagList implements Initializable, CreateTag.TagCreateListener {
    @FXML private ListView<Tag> tagList;
    @FXML private CreateTag createTagController;
    private ResourceBundle resourceBundle;
    private final ObservableList<Tag> tagData;
    private final Tags tags;

    public TagList() {
        this.tagData = new ObservableListWrapper<>(new ArrayList<>());
        this.tags = new CalistoFactory().tags();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        tagData.addAll(tags.all());
        tagList.setItems(tagData);
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
        createTagController.setListener(this);
    }

    @Override
    public void onTagCreated(Tag tag) {
        tagData.add(tag);
    }
}
