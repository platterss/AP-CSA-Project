package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label healthText;
    @FXML
    private Label monsterHealthText;
    @FXML
    private ImageView monsterSpriteView;
    @FXML
    private Label welcomeText;
    @FXML
    private Label question;
    @FXML
    private Button st;
    @FXML
    private Button r1;
    @FXML
    private Button r2;
    @FXML
    private Button r3;
    @FXML
    private Button next;

    private Monster monster;
    private Person hero;

    private Sprite heroSprite;
    private Sprite monsterSprite;

    private Calculation calculation;
    private int currentProblem;

    public HelloController() {
    }

    @FXML
    protected void onStart() {
        calculation = new Addition();
        monster = new Monster(300/*, 1*/);
        hero = new Person(100);
        monsterSprite = new Sprite(new Image(getClass().getResource("/chris.jpg").toExternalForm()));

        monsterSpriteView.setImage(monsterSprite.getView().getImage());

        st.setVisible(false);
        r1.setVisible(true);
        r2.setVisible(true);
        r3.setVisible(true);
        healthText.setVisible(true);
        monsterHealthText.setVisible(true);
        monsterSpriteView.setVisible(true);

        currentProblem = 1;
        getProblem();
    }

    @FXML
    protected void onR1Click() {
        checkAnswer(r1);
    }

    @FXML
    protected void onR2Click() {
        checkAnswer(r2);
    }

    @FXML
    protected void onR3Click() {
        checkAnswer(r3);
    }

    private void getProblem() {
        question.setText("Question: " + calculation.getExpression());

        ArrayList<Integer> answerBank = calculation.getAnswerCombo();

        r1.setText(String.valueOf(answerBank.get(0)));
        r2.setText(String.valueOf(answerBank.get(1)));
        r3.setText(String.valueOf(answerBank.get(2)));

        healthText.setText("Your health: " + hero.getHealth());
        monsterHealthText.setText("Monster's health: " + monster.getHealth());

        monsterSpriteView.setImage(monsterSprite.getView().getImage());
    }

    private void checkAnswer(Button clicked) {
        if (!next.isVisible()) {
            if (Integer.parseInt(clicked.getText()) == calculation.getResult()) {
                welcomeText.setText("Correct");
                next.setVisible(true);
                currentProblem++;
                monster.takeDamage();
                System.out.println(monster.getHealth());
                monsterSprite = new Sprite(new Image(getClass().getResource("/chris.jpg").toExternalForm()));
            } else {
                welcomeText.setText("Incorrect");
                hero.setHealth(hero.getHealth() - 10); // reduce the hero's health by 10
                System.out.println(hero.getHealth());
                monsterSprite = new Sprite(new Image(getClass().getResource("/chris_angry.jpg").toExternalForm()));
            }

            welcomeText.setVisible(true);
            healthText.setText("Your health: " + hero.getHealth());
            monsterHealthText.setText("Monster's health: " + monster.getHealth());
            monsterSpriteView.setImage(monsterSprite.getView().getImage());
        }
    }

    public void onNextClick() {
        next.setVisible(false);
        welcomeText.setVisible(false);
        if (currentProblem == 2) {
            calculation = new Subtraction();
        } else if (currentProblem == 3) {
            calculation = new Multiplication();
        } else if (currentProblem > 3) {
            System.exit(0);
        }
        getProblem();
    }
}