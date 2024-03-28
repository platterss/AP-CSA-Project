package com.example.demo2;

public class Person {
    private int health;

    public Person(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage() {
        this.setHealth(this.getHealth() - 10);
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

class Monster extends Person {
    public Monster(int health){
       super(health);
    }

    public void takeDamage() {
        this.setHealth(this.getHealth() - 100);
    }
}
