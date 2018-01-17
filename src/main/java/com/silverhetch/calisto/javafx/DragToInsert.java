package com.silverhetch.calisto.javafx;

import com.silverhetch.calisto.Calisto;
import com.silverhetch.calisto.CalistoFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.TransferMode.MOVE;

public class DragToInsert implements Initializable {
    private final Calisto calisto;

    public DragToInsert() {
        this.calisto = new CalistoFactory().calisto();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(MOVE);
        }
        dragEvent.consume();
    }

    public void onDragDropped(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()) {
            calisto.put(dragboard.getFiles().get(0));
            success = true;
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }
}
