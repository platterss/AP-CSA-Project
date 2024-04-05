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

public class Fight2Controller implements Initializable {
    private final String HERO_DEFAULT_SPRITE = "/Images/enriquez.jpg";
    private final String HERO_ALTERNATE_SPRITE = "/Images/enriquez_2.jpg";
    private final String OPPONENT_DEFAULT_SPRITE = "/Images/kenny.jpg";
    private final String OPPONENT_ALTERNATE_SPRITE = "/Images/kenny_2.jpg";

    public static int endHeroHealth;

    @FXML
    public Label mathExpressionTxt;
    @FXML
    public Label timeTxt;
    @FXML
    public Label resultTxt;
    @FXML
    public Label heroHealthTxt;
    @FXML
    public Label opponentHealthTxt;
    @FXML
    public Button answer1Btn;
    @FXML
    public Button answer2Btn;
    @FXML
    public Button answer3Btn;
    @FXML
    public Button nextBtn;
    @FXML
    public ImageView heroView;
    @FXML
    public ImageView opponentView;

    public Expression expression;
    public Entity hero;
    public Entity opponent;

    private Timeline timeline;
    private int timeMilliseconds;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hero = new Entity(Fight1Controller.endHeroHealth, HERO_DEFAULT_SPRITE);
        heroView.setImage(hero.getImage());
        heroHealthTxt.setText("Health: " + hero.getHealth());

        opponent = new Entity(500, OPPONENT_DEFAULT_SPRITE);
        opponentView.setImage(opponent.getImage());
        opponentHealthTxt.setText("Health: " + opponent.getHealth());

        generateQuestion();
    }

    private void updateTimer() {
        // Decrement the time by one millisecond
        timeMilliseconds -= 100;

        updateTimerLabel();

        // Stop the timeline and update UI when reaching zero
        if (timeMilliseconds <= 0) {
            timeline.stop();
            timeTxt.setText("0.0");

            resultTxt.setText("Out of time");
            hero.setHealth(hero.getHealth() - 10);
            updateScreen();
        }
    }

    private void updateTimerLabel() {
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
            endHeroHealth = hero.getHealth();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/fight3-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) timeTxt.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        // Create a Timeline that updates the timer every millisecond
        timeline = new Timeline(new KeyFrame(Duration.millis(100), evt -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void checkAnswer(Button button) {
        if (resultTxt.getText().equals("Choose an answer:")) {
            timeline.stop();
            if (Integer.parseInt(button.getText()) == expression.getAnswer()) {
                resultTxt.setText("Correct");
                opponent.setHealth(opponent.getHealth() - (int) (100 * (2 * Double.parseDouble(timeTxt.getText()) / 10)));

                hero.setImage(HERO_DEFAULT_SPRITE);
                opponent.setImage(OPPONENT_DEFAULT_SPRITE);
            } else {
                resultTxt.setText("Incorrect");
                hero.setHealth(hero.getHealth() - 10);

                hero.setImage(HERO_ALTERNATE_SPRITE);
                opponent.setImage(OPPONENT_ALTERNATE_SPRITE);
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
}
