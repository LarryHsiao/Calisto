package com.silverhetch.calisto.javafx;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoFile;
import com.silverhetch.calisto.CalistoFiles;
import com.silverhetch.calisto.javafx.dialog.AlertDialog;
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
    public ListView<CalistoFile> rootList;
    public TextField tagFilterField;
    public Label dragIndicator;
    public ListView<CalistoFile> contentList;

    private final CalistoFiles calistoFiles;
    private final ObservableList<CalistoFile> rootData;
    private final ObservableList<CalistoFile> contentData;


    public DragToInsert() {
        this.calistoFiles = new CalistoFactory().calisto();
        this.rootData = new ObservableListWrapper<>(new ArrayList<>());
        this.contentData = new ObservableListWrapper<>(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        contentList.setItems(contentData);
        contentList.setCellFactory(new Callback<ListView<CalistoFile>, ListCell<CalistoFile>>() {
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
    }

    private void onItemSelected(CalistoFile newValue) {
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
            new AlertDialog().show(e.getMessage());
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
