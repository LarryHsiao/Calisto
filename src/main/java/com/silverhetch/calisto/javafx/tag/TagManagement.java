package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoTag;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TagManagement implements Initializable {
    public ListView<CalistoTag> tagList;
    private final ObservableList<CalistoTag> tagData;

    public TagManagement() {
        tagData = new ObservableListWrapper<>(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    }

    public void onCreateClick(MouseEvent mouseEvent) {

    }
}
