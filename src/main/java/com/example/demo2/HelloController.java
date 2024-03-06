package com.example.demo2;

import com.example.demo2.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
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
    private Monster monster = new Monster(300, 1);
    @FXML
    private Person hero = new Person(100);

    private Calculation calculation;
    private int currentProblem;

    public HelloController() {
    }

    @FXML
    protected void onStart() {
        calculation = new Addition();

        st.setVisible(false);
        r1.setVisible(true);
        r2.setVisible(true);
        r3.setVisible(true);

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
    }

    private void checkAnswer(Button clicked) {
        if (Integer.parseInt(clicked.getText()) == calculation.getResult()) {
            welcomeText.setText("Correct");
            next.setVisible(true);
            currentProblem++;
            monster.takeDamage();
            System.out.println(monster.getHealth());
            hero.setHealth(hero.getHealth() - 10); // reduce the hero's health by 10
        } else {
            welcomeText.setText("Incorrect");
        }
        welcomeText.setVisible(true);
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

    @FXML
    private void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}