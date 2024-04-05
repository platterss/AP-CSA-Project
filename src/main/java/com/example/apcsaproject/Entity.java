package com.example.apcsaproject;

import javafx.scene.image.Image;

import java.util.Objects;

public class Entity {
    private int health;
    private Image image;

    public Entity(int health, String imagePath) {
        this.health = health;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
    }

    public void setHealth(int health) {
        this.health = health;

        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void setImage(String imagePath) {
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
    }

    public int getHealth() {
        return health;
    }

    public Image getImage() {
        return image;
    }
}
