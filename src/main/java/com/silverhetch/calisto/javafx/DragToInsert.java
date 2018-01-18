package com.silverhetch.calisto.javafx;

import com.silverhetch.calisto.CalistoFactory;
import com.silverhetch.calisto.CalistoFile;
import com.silverhetch.calisto.CalistoFiles;
import com.silverhetch.calisto.javafx.dialog.AlertDialog;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    @FXML
    public ListView<CalistoFile> listView;

    private final CalistoFiles calistoFiles;
    private final ObservableList<CalistoFile> listData;

    public DragToInsert() {
        this.calistoFiles = new CalistoFactory().calisto();
        this.listData = new ObservableListWrapper<>(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listData.addAll(calistoFiles.all());
        listView.setItems(listData);
        listView.setCellFactory(new Callback<ListView<CalistoFile>, ListCell<CalistoFile>>() {
            @Override
            public ListCell<CalistoFile> call(ListView<CalistoFile> param) {
                return new ListCell<CalistoFile>(){
                    @Override
                    protected void updateItem(CalistoFile item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item !=null) {
                            setText(item.name());
                        }
                    }
                };
            }
        });
    }

    public void onDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(MOVE);
        }
        dragEvent.consume();
    }

    public void onDragDropped(DragEvent dragEvent){
        try {
            Dragboard dragboard = dragEvent.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                List<CalistoFile> newCalistoFile = new ArrayList<>();
                for (File newFile : dragboard.getFiles()) {
                    CalistoFile calistoFile = calistoFiles.put(newFile);
                    newCalistoFile.add(calistoFile);
                }
                listData.addAll(newCalistoFile);
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        }catch (Exception e){
            new AlertDialog().show(e.getMessage());
        }
    }
}
