package com.example.demo2;

public class Person {
  private int health;

  public Person(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}

class Monster extends Person {
    private int type;

    public Monster(int health, int type){
       super(health);
       this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    public void takeDamage() {
        this.setHealth(this.getHealth() - 100);
    }
}
