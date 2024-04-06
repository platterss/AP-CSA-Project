package com.example.apcsaproject.controller;

import com.example.apcsaproject.CSAProject;
import javafx.scene.control.Button;

public class EndController {
    public Button playAgainBtn, exitBtn;

    public void onPlayAgainBtnClick() {
        CSAProject.loadScene("/FXML/main-view.fxml", playAgainBtn, null);
    }

    public void onExitBtnClick() {
        System.exit(0);
    }
}
