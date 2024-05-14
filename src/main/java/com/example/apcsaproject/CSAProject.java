package com.example.apcsaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/main-view.fxml"));
        Region region = loader.load();

        Scene scene = createWindow(region);

        stage.setScene(scene);
        stage.show();
    }

    public static void loadScene(String fxmlPath, Node node, Consumer<FXMLLoader> controllerSetup) {
        try {
            FXMLLoader loader = new FXMLLoader(CSAProject.class.getResource(fxmlPath));
            Region region = loader.load();

            if (controllerSetup != null) {
                controllerSetup.accept(loader);
            }

            Scene scene = createWindow(region);

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Scene createWindow(Region region) {
        double origW = 600;
        double origH = 400;

        Group group = new Group(region);
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);

        Scene scene = new Scene(rootPane, origW, origH);

        group.scaleXProperty().bind(scene.widthProperty().divide(origW));
        group.scaleYProperty().bind(scene.heightProperty().divide(origH));

        return scene;
    }
}
