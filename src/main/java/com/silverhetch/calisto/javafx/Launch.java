package com.silverhetch.calisto.javafx;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/css/General.css").toURI().toString());

        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"), new ResourceBundleFactory().instance());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
