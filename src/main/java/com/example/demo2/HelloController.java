package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label healthText;
    @FXML
    private Label monsterHealthText;
    @FXML
    private ImageView heroSpriteView;
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
    private int currentMonster;

    public HelloController() {
    }

    @FXML
    protected void onStart() {
        currentMonster = 1;
        currentProblem = 1;
        calculation = new Addition();
        monster = new Monster(300);
        hero = new Person(500); // Only super high for testing
        heroSprite = new Sprite(getHeroNormal());
        monsterSprite = new Sprite(getMonsterNormal());

        heroSpriteView.setImage(heroSprite.getView().getImage());
        monsterSpriteView.setImage(monsterSprite.getView().getImage());

        st.setVisible(false);
        r1.setVisible(true);
        r2.setVisible(true);
        r3.setVisible(true);
        healthText.setVisible(true);
        heroSpriteView.setVisible(true);
        monsterHealthText.setVisible(true);
        monsterSpriteView.setVisible(true);

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

        heroSprite = new Sprite(getHeroNormal());
        monsterSprite = new Sprite(getMonsterNormal());

        heroSpriteView.setImage(heroSprite.getView().getImage());
        monsterSpriteView.setImage(monsterSprite.getView().getImage());
    }

    private void checkAnswer(Button clicked) {
        if (!next.isVisible()) {
            if (Integer.parseInt(clicked.getText()) == calculation.getResult()) {
                welcomeText.setText("Correct");
                next.setVisible(true);
                monster.takeDamage();
                System.out.println(monster.getHealth());
                heroSprite = new Sprite(getHeroNormal());
                monsterSprite = new Sprite(getMonsterNormal());
                currentProblem++;
            } else {
                welcomeText.setText("Incorrect");
                hero.setHealth(hero.getHealth() - 10);
                System.out.println(hero.getHealth());
                heroSprite = new Sprite(getHeroAngry());
                monsterSprite = new Sprite(getMonsterAngry());
            }

            if (hero.getHealth() <= 0) {
                System.out.println("No health");
                System.exit(0);
            }

            if (monster.getHealth() <= 0) {
                currentMonster++;
                monster = new Monster(300);
            }

            welcomeText.setVisible(true);
            healthText.setText("Your health: " + hero.getHealth());
            monsterHealthText.setText("Monster's health: " + monster.getHealth());
            heroSpriteView.setImage(heroSprite.getView().getImage());
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
            if (currentMonster > 3) {
                System.out.println("No more monsters");
                System.exit(0);
            }

            currentProblem = 1;
            calculation = new Addition();
        }
        getProblem();
    }

    private Image getHeroNormal() {
        return new Image(Objects.requireNonNull(getClass().getResource("/enriquez.jpg")).toExternalForm());
    }

    private Image getHeroAngry() {
        return new Image(Objects.requireNonNull(getClass().getResource("/enriquez_angry.jpg")).toExternalForm());
    }

    private Image getMonsterNormal() {
        String jpg = "";

        if (currentMonster == 1) {
            jpg = "/chris.jpg";
        }

        if (currentMonster == 2) {
            jpg = "/kenny.jpg";
        }

        if (currentMonster == 3) {
            jpg = "/daniel.jpg";
        }

        return new Image(Objects.requireNonNull(getClass().getResource(jpg)).toExternalForm());
    }

    private Image getMonsterAngry() {
        String jpg = "";

        if (currentMonster == 1) {
            jpg = "/chris_angry.jpg";
        }

        if (currentMonster == 2) {
            jpg = "/kenny_disappointed.jpg";
        }

        if (currentMonster == 3) {
            jpg = "/daniel_HANDSOME.jpg";
        }

        return new Image(Objects.requireNonNull(getClass().getResource(jpg)).toExternalForm());
    }
}