package com.example.apcsaproject.controller;

import com.example.apcsaproject.Expression;
import com.example.apcsaproject.Entity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Fight1Controller implements Initializable {
    @FXML
    public Label mathExpressionTxt;
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
    public Entity monster;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateQuestion();
    }

    public void onAnswer1Click(ActionEvent actionEvent) {
    }

    public void onAnswer2Click(ActionEvent actionEvent) {
    }

    public void onAnswer3Click(ActionEvent actionEvent) {
    }

    public void onNextBtnClick(ActionEvent actionEvent) {
    }

    private void generateQuestion() {
        expression = new Expression();
        List<Integer> possibleAnswers = expression.getAnswerCombo();

        resultTxt.setText("Choose an answer:");
        mathExpressionTxt.setText(expression.getExpression());
        answer1Btn.setText(Integer.toString(possibleAnswers.get(0)));
        answer2Btn.setText(Integer.toString(possibleAnswers.get(1)));
        answer3Btn.setText(Integer.toString(possibleAnswers.get(2)));
    }
}
