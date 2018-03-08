package com.silverhetch.calisto.javafx.object;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoFile;
import com.silverhetch.calisto.CalistoFiles;
import com.silverhetch.calisto.javafx.utility.ExceptionDialog;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.TransferMode.MOVE;

public class ObjectList implements Initializable {
    @FXML private ListView<CalistoFile> rootList;
    @FXML private TextField tagFilterField;
    @FXML private Label dragIndicator;
    @FXML private TreeView<CalistoFile> objectTree;

    private final CalistoFiles calistoFiles;
    private final ObservableList<CalistoFile> rootData;
    private ResourceBundle resource;

    public ObjectList() {
        this.calistoFiles = new CalistoFactory().objects();
        this.rootData = new ObservableListWrapper<>(new ArrayList<>());
        this.resource = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resource = resources;
        rootData.addAll(calistoFiles.all());
        rootList.setItems(rootData);
        rootList.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> onItemSelected(newValue));
        rootList.setCellFactory(new Callback<ListView<CalistoFile>, ListCell<CalistoFile>>() {
            @Override
            public ListCell<CalistoFile> call(ListView<CalistoFile> param) {
                return new ListCell<CalistoFile>() {
                    @Override
                    protected void updateItem(CalistoFile item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.name());
                    }
                };
            }
        });
        objectTree.setShowRoot(false);
        objectTree.setRoot(new TreeItem<>());
        objectTree.setCellFactory(new Callback<TreeView<CalistoFile>, TreeCell<CalistoFile>>() {
            @Override
            public TreeCell<CalistoFile> call(TreeView<CalistoFile> param) {
                return new TreeCell<CalistoFile>() {
                    @Override
                    protected void updateItem(CalistoFile item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty || item == null ? "" : item.name());
                    }
                };
            }
        });
        objectTree.setOnMouseClicked(event -> {
            final TreeItem<CalistoFile> clickedItem = objectTree.getSelectionModel().getSelectedItem();
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                try {
                    clickedItem.getValue().execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    new ExceptionDialog().showDialog(e);
                }
            }

            if (event.getButton() == MouseButton.SECONDARY) {
                showContextMenu(clickedItem, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void showContextMenu(TreeItem<CalistoFile> clickedItem, double screenX, double screenY) {
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem item = new MenuItem(resource.getString("dragToInsert.attachTag"));
        item.setOnAction(event -> {
            try {
                new AttachTagDialog(resource).show(clickedItem.getValue());
            } catch (IOException e) {
                e.printStackTrace();
                new ExceptionDialog().showDialog(e);
            }
        });
        contextMenu.getItems().add(item);
        contextMenu.show(objectTree, screenX, screenY);
    }

    private void onItemSelected(CalistoFile newValue) {
        final TreeItem<CalistoFile> root = objectTree.getRoot();
        root.getChildren().clear();
        if (newValue == null) {
            return;
        }
        buildSubItems(root, newValue);
    }

    private void buildSubItems(TreeItem<CalistoFile> node, CalistoFile object) {
        for (CalistoFile contentObject : object.subFiles()) {
            TreeItem<CalistoFile> objectItem = new TreeItem<>(contentObject);
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
                List<CalistoFile> newCalistoFile = new ArrayList<>();
                for (File newFile : dragboard.getFiles()) {
                    CalistoFile calistoFile = calistoFiles.put(newFile);
                    newCalistoFile.add(calistoFile);
                }
                rootData.addAll(newCalistoFile);
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
            rootData.addAll(calistoFiles.all());
        } else {
            rootData.addAll(calistoFiles.byTag(tagFilterField.getText()));
        }
    }

    public void onDragExited(DragEvent dragEvent) {
        dragIndicator.setVisible(false);
    }
}
