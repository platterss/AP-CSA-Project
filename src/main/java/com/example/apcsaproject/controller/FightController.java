package com.example.apcsaproject.controller;

import com.example.apcsaproject.Expression;
import com.example.apcsaproject.Entity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class FightController implements Initializable {
    @FXML
    protected Label mathExpressionTxt, timeTxt, resultTxt, heroHealthTxt, opponentHealthTxt;
    @FXML
    protected Button answer1Btn, answer2Btn, answer3Btn, nextBtn;
    @FXML
    protected ImageView heroView, opponentView;

    protected Expression expression;
    protected Entity hero;
    protected Entity opponent;

    private Timeline timeline;
    private int timeMilliseconds;
    protected int initialHeroHealth = 100;

    protected abstract String getNextFight();
    protected abstract String getOpponentDefaultSprite();
    protected abstract String getOpponentAlternateSprite();

    private boolean heroHealthHasBeenSet = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeEntities();
        generateQuestion();
    }

    protected void updateTimer() {
        timeMilliseconds -= 100;

        updateTimerLabel();

        if (timeMilliseconds <= 0) {
            timeline.stop();
            timeTxt.setText("0.0");

            resultTxt.setText("Out of time");
            hero.setHealth(hero.getHealth() - 10);
            updateScreen();
        }
    }

    protected void updateTimerLabel() {
        int secondsPart = timeMilliseconds / 1000;
        int milliSecondsPart = timeMilliseconds % 1000;
        timeTxt.setText(secondsPart + "." + String.format("%03d", milliSecondsPart));
    }

    public void onAnswer1Click() {
        checkAnswer(answer1Btn);
    }

    public void onAnswer2Click() {
        checkAnswer(answer2Btn);
    }

    public void onAnswer3Click() {
        checkAnswer(answer3Btn);
    }

    public void onNextBtnClick() {
        if (opponent.getHealth() > 0) {
            generateQuestion();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(getNextFight()));
                Parent root = loader.load();

                FightController nextController = loader.getController();
                nextController.setHeroHealth(hero.getHealth());
                nextController.initializeEntities();

                Stage stage = (Stage) timeTxt.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setHeroHealth(int health) {
        if (hero == null) {
            hero = new Entity(health, getHeroDefaultSprite());
        } else {
            hero.setHealth(health);
        }
        heroHealthHasBeenSet = true;
    }

    public void initializeEntities() {
        if (!heroHealthHasBeenSet) {
            hero = new Entity(initialHeroHealth, getHeroDefaultSprite());
        }

        heroView.setImage(hero.getImage());
        heroHealthTxt.setText("Health: " + hero.getHealth());

        opponent = new Entity(500, getOpponentDefaultSprite());
        opponentView.setImage(opponent.getImage());
        opponentHealthTxt.setText("Health: " + opponent.getHealth());
    }

    protected void generateQuestion() {
        if (timeline != null) {
            timeline.stop();
        }

        expression = new Expression();
        List<Integer> possibleAnswers = expression.getAnswerCombo();

        nextBtn.setVisible(false);
        resultTxt.setText("Choose an answer:");
        mathExpressionTxt.setText(expression.getExpression());
        answer1Btn.setText(Integer.toString(possibleAnswers.get(0)));
        answer2Btn.setText(Integer.toString(possibleAnswers.get(1)));
        answer3Btn.setText(Integer.toString(possibleAnswers.get(2)));

        timeMilliseconds = 5 * 1000;
        updateTimerLabel();

        timeline = new Timeline(new KeyFrame(Duration.millis(100), evt -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    protected void checkAnswer(Button button) {
        if (resultTxt.getText().equals("Choose an answer:")) {
            timeline.stop();
            if (Integer.parseInt(button.getText()) == expression.getAnswer()) {
                resultTxt.setText("Correct");
                opponent.setHealth(opponent.getHealth() - (int) (100 * (2 * Double.parseDouble(timeTxt.getText()) / 10)));

                hero.setImage(getHeroDefaultSprite());
                opponent.setImage(getOpponentDefaultSprite());
            } else {
                resultTxt.setText("Incorrect");
                hero.setHealth(hero.getHealth() - 10);
                opponent.setHealth(opponent.getHealth() + 30);

                hero.setImage(getHeroAlternateSprite());
                opponent.setImage(getOpponentAlternateSprite());
            }
            nextBtn.setVisible(true);
            updateScreen();
        }
    }

    private void updateScreen() {
        heroView.setImage(hero.getImage());
        heroHealthTxt.setText("Health: " + hero.getHealth());

        opponentView.setImage(opponent.getImage());
        opponentHealthTxt.setText("Health: " + opponent.getHealth());
    }

    private String getHeroDefaultSprite() {
        return "/Images/enriquez.jpg";
    }

    private String getHeroAlternateSprite() {
        return "/Images/enriquez_2.jpg";
    }
}
