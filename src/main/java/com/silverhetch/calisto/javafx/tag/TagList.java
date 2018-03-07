package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoTag;
import com.silverhetch.calisto.CalistoTags;
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
    @FXML private ListView<CalistoTag> tagList;
    @FXML private CreateTag createTagController;
    private ResourceBundle resourceBundle;
    private final ObservableList<CalistoTag> tagData;
    private final CalistoTags tags;

    public TagList() {
        this.tagData = new ObservableListWrapper<>(new ArrayList<>());
        this.tags = new CalistoFactory().tags();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        tagData.addAll(tags.all());
        tagList.setItems(tagData);
        tagList.setCellFactory(new Callback<ListView<CalistoTag>, ListCell<CalistoTag>>() {
            @Override
            public ListCell<CalistoTag> call(ListView<CalistoTag> param) {
                return new ListCell<CalistoTag>() {
                    @Override
                    protected void updateItem(CalistoTag item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.name());
                    }
                };
            }
        });
        createTagController.setListener(this);
    }

    @Override public void onTagCreated(CalistoTag tag) {
        tagData.add(tag);
    }
}
