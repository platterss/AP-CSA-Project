package com.example.apcsaproject.controller;

import com.example.apcsaproject.CSAProject;
import com.example.apcsaproject.Expression;
import com.example.apcsaproject.Entity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class FightController implements Initializable {
    private Expression expression;
    private Entity hero;
    private Entity opponent;
    private Timeline timeline;
    private int timeMilliseconds;
    private boolean heroHealthHasBeenSet = false;

    @FXML private Label mathExpressionTxt, timeTxt, resultTxt, heroHealthTxt, opponentHealthTxt;
    @FXML private Button answer1Btn, answer2Btn, answer3Btn, nextBtn;
    @FXML private ImageView heroView, opponentView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeEntities();
        generateQuestion();
    }

    @FXML
    public void onAnswer1Click() {
        checkAnswer(answer1Btn);
    }

    @FXML
    public void onAnswer2Click() {
        checkAnswer(answer2Btn);
    }

    @FXML
    public void onAnswer3Click() {
        checkAnswer(answer3Btn);
    }

    @FXML
    public void onNextBtnClick() {
        if (hero.getHealth() == 0) {
            CSAProject.loadScene("/FXML/gameOver-view.fxml", nextBtn, null);
        } else if (opponent.getHealth() > 0) {
            generateQuestion();
        } else {
            CSAProject.loadScene(getNextFight(), nextBtn, loader -> {
                if (!getNextFight().equals("/FXML/congratulations-view.fxml")) {
                    FightController nextController = loader.getController();
                    nextController.setHeroHealth(hero.getHealth());
                    nextController.initializeEntities();
                }
            });
        }
    }

    protected abstract String getNextFight();
    protected abstract String getOpponentDefaultSprite();
    protected abstract String getOpponentAlternateSprite();

    private void setHeroHealth(int health) {
        if (hero == null) {
            hero = new Entity(health, getHeroDefaultSprite());
        } else {
            hero.setHealth(health);
        }
        heroHealthHasBeenSet = true;
    }

    private void initializeEntities() {
        if (!heroHealthHasBeenSet) {
            int initialHeroHealth = 100;
            hero = new Entity(initialHeroHealth, getHeroDefaultSprite());
        }

        opponent = new Entity(500, getOpponentDefaultSprite());
        updateScreen();
    }

    private void generateQuestion() {
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

    private void checkAnswer(Button button) {
        if (resultTxt.getText().equals("Choose an answer:")) {
            timeline.stop();
            if (Integer.parseInt(button.getText()) == expression.getAnswer()) {
                double time = Double.parseDouble(timeTxt.getText().split(" ")[1].replaceAll("[a-zA-Z]", ""));

                resultTxt.setText("Correct");
                opponent.setHealth(opponent.getHealth() - (int) (100 * (2 * time) / 10));

                setDefaultSprites();
            } else {
                resultTxt.setText("Incorrect");
                hero.setHealth(hero.getHealth() - 10);
                opponent.setHealth(opponent.getHealth() + 30);

                setAlternateSprites();
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

    private void updateTimer() {
        timeMilliseconds -= 100;

        updateTimerLabel();

        if (timeMilliseconds <= 0) {
            timeline.stop();
            timeTxt.setText("0.0");

            resultTxt.setText("Out of time");
            hero.setHealth(hero.getHealth() - 10);
            setAlternateSprites();
            nextBtn.setVisible(true);

            updateScreen();
        }
    }

    private void updateTimerLabel() {
        double seconds = timeMilliseconds / 1000.0;
        timeTxt.setText("Time: " + String.format("%.1f", seconds) + " seconds");
    }

    private String getHeroDefaultSprite() {
        return "/Images/enriquez.jpg";
    }

    private String getHeroAlternateSprite() {
        return "/Images/enriquez_2.jpg";
    }

    private void setDefaultSprites() {
        hero.setImage(getHeroDefaultSprite());
        opponent.setImage(getOpponentDefaultSprite());
    }

    private void setAlternateSprites() {
        hero.setImage(getHeroAlternateSprite());
        opponent.setImage(getOpponentAlternateSprite());
    }
}
