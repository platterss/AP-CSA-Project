package com.example.apcsaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.function.Consumer;

public class CSAProject extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(CSAProject.class.getResource("/FXML/main-view.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void loadScene(String fxmlPath, Node node, Consumer<FXMLLoader> controllerSetup) {
        try {
            FXMLLoader loader = new FXMLLoader(CSAProject.class.getResource(fxmlPath));
            Parent root = loader.load();

            if (controllerSetup != null) {
                controllerSetup.accept(loader);
            }

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
