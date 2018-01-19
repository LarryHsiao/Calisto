package com.silverhetch.calisto.javafx;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoObject;
import com.silverhetch.calisto.CalistoObjects;
import com.silverhetch.calisto.javafx.utility.AlertDialog;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.TransferMode.MOVE;

public class DragToInsert implements Initializable {
    public ListView<CalistoObject> rootList;
    public TextField tagFilterField;
    public Label dragIndicator;
    public ListView<CalistoObject> contentList;

    private final CalistoObjects calistoObjects;
    private final ObservableList<CalistoObject> rootData;
    private final ObservableList<CalistoObject> contentData;


    public DragToInsert() {
        this.calistoObjects = new CalistoFactory().calisto();
        this.rootData = new ObservableListWrapper<>(new ArrayList<>());
        this.contentData = new ObservableListWrapper<>(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootData.addAll(calistoObjects.all());
        rootList.setItems(rootData);
        rootList.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> onItemSelected(newValue));
        rootList.setCellFactory(new Callback<ListView<CalistoObject>, ListCell<CalistoObject>>() {
            @Override
            public ListCell<CalistoObject> call(ListView<CalistoObject> param) {
                return new ListCell<CalistoObject>() {
                    @Override
                    protected void updateItem(CalistoObject item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.name());
                    }
                };
            }
        });

        contentList.setItems(contentData);
        contentList.setCellFactory(new Callback<ListView<CalistoObject>, ListCell<CalistoObject>>() {
            @Override
            public ListCell<CalistoObject> call(ListView<CalistoObject> param) {
                return new ListCell<CalistoObject>() {
                    @Override
                    protected void updateItem(CalistoObject item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.name());
                    }
                };
            }
        });
    }

    private void onItemSelected(CalistoObject newValue) {
        contentData.clear();
        contentData.addAll(newValue.subFiles());
    }

    public void onDragOver(DragEvent dragEvent) {
        dragIndicator.setVisible(true);
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(MOVE);
        }
        dragEvent.consume();
    }

    public void onDragDropped(DragEvent dragEvent) {
        try {
            dragIndicator.setVisible(false);
            Dragboard dragboard = dragEvent.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                List<CalistoObject> newCalistoObject = new ArrayList<>();
                for (File newFile : dragboard.getFiles()) {
                    CalistoObject calistoObject = calistoObjects.put(newFile);
                    newCalistoObject.add(calistoObject);
                }
                rootData.addAll(newCalistoObject);
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        } catch (Exception e) {
            new AlertDialog().show(e.getMessage());
        }
    }

    public void onFilterChanged(ActionEvent actionEvent) {
        if (tagFilterField.getText() == null) {
            return;
        }
        rootData.clear();
        if (tagFilterField.getText().isEmpty()) {
            rootData.addAll(calistoObjects.all());
        } else {
            rootData.addAll(calistoObjects.byTag(tagFilterField.getText()));
        }
    }

    public void onDragExited(DragEvent dragEvent) {
        dragIndicator.setVisible(false);
    }
}
