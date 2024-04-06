package com.example.apcsaproject.controller;

import com.example.apcsaproject.CSAProject;
import javafx.scene.control.Button;

public class MainController {
    public Button startBtn;

    public void onStartBtnClick() {
        CSAProject.loadScene("/FXML/fight1-view.fxml", startBtn, null);
    }
}
