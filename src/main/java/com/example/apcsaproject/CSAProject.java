package com.example.apcsaproject;

import com.example.apcsaproject.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class CSAProject extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(CSAProject.class.getResource("/FXML/main-view.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void changeScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
    }
}
