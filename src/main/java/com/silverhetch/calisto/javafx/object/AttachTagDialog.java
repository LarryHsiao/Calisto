package com.silverhetch.calisto.javafx.object;

import com.silverhetch.calisto.CalistoObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;

import java.io.IOException;
import java.util.ResourceBundle;

public class AttachTagDialog {
    private final ResourceBundle resourceBundle;

    public AttachTagDialog(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void show(CalistoObject object) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AttachTag.fxml"));
        loader.setResources(resourceBundle);
        loader.setController(new AttachTag(object));
        Parent parent = loader.load();

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("dragToInsert.attachTag"));
        dialog.setGraphic(parent);
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> dialog.close());
        dialog.showAndWait();
    }
}