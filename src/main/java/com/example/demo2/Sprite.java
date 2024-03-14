package com.example.demo2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private ImageView imageView;
    private double x;
    private double y;

    public Sprite(Image image) {
        this.imageView = new ImageView(image);
    }

    public ImageView getView() {
        return imageView;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        imageView.setX(x);
        imageView.setY(y);
    }
}