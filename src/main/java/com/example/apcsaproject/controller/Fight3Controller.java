package com.example.apcsaproject.controller;

public class Fight3Controller extends FightController {
    @Override
    protected String getNextFight() {
        return "/FXML/congratulations-view.fxml";
    }

    @Override
    protected String getOpponentDefaultSprite() {
        return "/Images/chris.jpg";
    }

    @Override
    protected String getOpponentAlternateSprite() {
        return "/Images/chris_2.jpg";
    }
}
