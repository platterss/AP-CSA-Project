package com.example.apcsaproject;

import javafx.scene.image.Image;

public class Entity {
    private int health;
    private Image image;

    public Entity(int health, String imagePath) {
        this.health = health;
        this.image = new Image(imagePath);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setImage(String imagePath) {
        this.image = new Image(imagePath);
    }

    public int getHealth() {
        return health;
    }

    public Image getImage() {
        return image;
    }
}
