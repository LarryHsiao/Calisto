package com.silverhetch.calisto.javafx.object;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoObject;
import com.silverhetch.calisto.CalistoObjects;
import com.silverhetch.calisto.javafx.utility.ExceptionDialog;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.TransferMode.MOVE;

public class DragToInsert implements Initializable {
    @FXML private ListView<CalistoObject> rootList;
    @FXML private TextField tagFilterField;
    @FXML private Label dragIndicator;
    @FXML private TreeView<CalistoObject> objectTree;

    private final CalistoObjects calistoObjects;
    private final ObservableList<CalistoObject> rootData;

    public DragToInsert() {
        this.calistoObjects = new CalistoFactory().objects();
        this.rootData = new ObservableListWrapper<>(new ArrayList<>());
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
        objectTree.setShowRoot(false);
        objectTree.setRoot(new TreeItem<>());
        objectTree.setCellFactory(new Callback<TreeView<CalistoObject>, TreeCell<CalistoObject>>() {
            @Override
            public TreeCell<CalistoObject> call(TreeView<CalistoObject> param) {
                return new TreeCell<CalistoObject>() {
                    @Override
                    protected void updateItem(CalistoObject item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty || item == null ? "" : item.name());
                    }
                };
            }
        });
        objectTree.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    TreeItem<CalistoObject> clickedItem = objectTree.getSelectionModel().getSelectedItem();
                    clickedItem.getValue().execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    new ExceptionDialog().showDialog(e);
                }
            }
        });
    }

    private void onItemSelected(CalistoObject newValue) {
        final TreeItem<CalistoObject> root = objectTree.getRoot();
        root.getChildren().clear();
        if (newValue == null) {
            return;
        }
        buildSubItems(root, newValue);
    }

    private void buildSubItems(TreeItem<CalistoObject> node, CalistoObject object) {
        for (CalistoObject contentObject : object.subFiles()) {
            TreeItem<CalistoObject> objectItem = new TreeItem<>(contentObject);
            node.getChildren().add(objectItem);
            buildSubItems(objectItem, contentObject);
        }
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
            new ExceptionDialog().showDialog(e);
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
