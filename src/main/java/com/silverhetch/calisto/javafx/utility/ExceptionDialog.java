package com.silverhetch.calisto.javafx.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ExceptionDialog {
    public void showDialog(Exception e) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/bundle");
        Alert alert = new Alert(ERROR);
        alert.setTitle(bundle.getString("dialogException.title"));
        alert.setHeaderText(bundle.getString("dialogException.header"));
        alert.setContentText(e.getMessage());

        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        final String exceptionText = stringWriter.toString();

        Label label = new Label(bundle.getString("dialogException.contentIndicator"));

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
}
