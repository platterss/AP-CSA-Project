package com.example.apcsaproject.controller;

public class Fight1Controller extends FightController {
    @Override
    protected String getNextFight() {
        return "/FXML/fight2-view.fxml";
    }

    @Override
    protected String getOpponentDefaultSprite() {
        return "/Images/daniel.jpg";
    }

    @Override
    protected String getOpponentAlternateSprite() {
        return "/Images/daniel_2.jpg";
    }
}
