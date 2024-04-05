package com.example.apcsaproject.controller;

import com.example.apcsaproject.CSAProject;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    private CSAProject mainApp;
    public Button startBtn;

    public void setMainApp(CSAProject mainApp) {
        this.mainApp = mainApp;
    }

    public void onStartBtnClick() {
        try {
            mainApp.changeScene("/FXML/fight1-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
