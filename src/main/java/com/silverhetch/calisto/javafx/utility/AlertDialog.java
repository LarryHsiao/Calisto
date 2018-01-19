package com.silverhetch.calisto.javafx.utility;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static javafx.stage.Modality.APPLICATION_MODAL;

public final class AlertDialog {

    public void show(String message) {
        ResourceBundle resource = ResourceBundle.getBundle("i18n/bundle");
        final Stage stage = new Stage();
        stage.initModality(APPLICATION_MODAL);
        stage.setTitle(resource.getString("app.alert"));
        stage.setMinWidth(250);

        final Label messageLabel = new Label();
        messageLabel.setText(message);

        final Button closeButton = new Button();
        closeButton.setText(resource.getString("app.confirm"));
        closeButton.setOnAction(e -> stage.close());

        final VBox rootLayout = new VBox();
        rootLayout.getChildren().addAll(messageLabel, closeButton);
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPadding(new Insets(16));

        final Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
