package com.silverhetch.calisto.javafx.object;

import com.silverhetch.calisto.CalistoFile;
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

    public void show(CalistoFile object) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AttachTag.fxml"));
        loader.setResources(resourceBundle);
        Parent parent = loader.load();
        ((AttachTag) loader.getController()).setup(object);

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(resourceBundle.getString("dragToInsert.attachTag"));
        dialog.setGraphic(parent);
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> dialog.close());
        dialog.showAndWait();
    }
}
