package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//imported for sprites
import javafx.scene.image.Image;
import javafx.scene.Group;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int INITIAL_X = 200;
    private static final int INITIAL_Y = 200;
    private static final int SPRITE_WIDTH = 50;
    private static final int SPRITE_HEIGHT = 50;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Title");

        //Sprites
        Image spriteImage = new Image(getClass().getResource("/chris02.jpg").toExternalForm());
        Sprite playerSprite = new Sprite(spriteImage);
        playerSprite.setPosition(INITIAL_X, INITIAL_Y);
        Group root = new Group();
        root.getChildren().add(playerSprite.getView());
        //end of sprites

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}