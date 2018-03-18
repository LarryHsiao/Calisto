package com.silverhetch.calisto.javafx.tag;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.javafx.component.ListImageViewFactory;
import com.silverhetch.calisto.tagging.Tag;
import com.silverhetch.calisto.tagging.Tags;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static javafx.event.ActionEvent.ACTION;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.OK;

public class TagList implements Initializable {
    private final static int IMAGE_SIZE = 25;
    @FXML private ListView<Tag> tagList;
    private ResourceBundle resourceBundle;
    private final Tags tags;
    private final ObservableList<Tag> list;

    public TagList() {
        this.list = new ObservableListWrapper<>(new ArrayList<>());
        this.tags = new CalistoFactory().tags();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        tagList.setItems(list);
        tagList.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>() {
            @Override
            public ListCell<Tag> call(ListView<Tag> param) {
                return new ListCell<Tag>() {
                    @Override
                    protected void updateItem(Tag item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                            setGraphic(null);
                        } else {
                            setText(item.name());
                            setGraphic(new ListImageViewFactory(item.imageUri()).imageView());
                        }
                    }
                };
            }
        });
        tagList.setOnKeyPressed(event -> {
            if (!tagList.isFocused()) {
                return;
            }
            Alert alert = new Alert(CONFIRMATION);
            alert.setTitle(resources.getString("app.confirm"));
            alert.setContentText(MessageFormat.format(resources.getString("app.confirmDelete"), tagList.getSelectionModel().getSelectedItem().name()));
            alert.getDialogPane().lookupButton(OK).addEventFilter(ACTION, confirmEvent -> {
                tagList.getSelectionModel().getSelectedItem().delete();
                loadList();
            });
            alert.showAndWait();
        });
        loadList();
    }

    public void loadList() {
        list.clear();
        list.addAll(Arrays.asList(tags.all()));
    }

    public Tag selectedTag() {
        return tagList.getSelectionModel().getSelectedItems().get(0);
    }
}
