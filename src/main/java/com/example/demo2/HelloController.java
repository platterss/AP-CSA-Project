package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public HelloController() {
    }

    @FXML
    protected void onStart(){
        System.out.println("stuff");
        question.setText("question: " + "5x5");
        st.setVisible(false);
        r1.setVisible(true);
        r1.setText("35");
        r2.setVisible(true);
        r2.setText("50000");
        r3.setVisible(true);
        r3.setText("25");

    }
    @FXML
    protected void onR1Click() {
        welcomeText.setText("Incorrect");
    }
    @FXML
    protected void onR2Click() {
        welcomeText.setText("Incorrect");
    }
    @FXML
    protected void onR3Click() {
        welcomeText.setText("Correct");
    }
}