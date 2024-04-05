package com.example.apcsaproject.controller;

public class Fight2Controller extends FightController {
    @Override
    protected String getNextFight() {
        return "/FXML/fight3-view.fxml";
    }

    @Override
    protected String getOpponentDefaultSprite() {
        return "/Images/kenny.jpg";
    }

    @Override
    protected String getOpponentAlternateSprite() {
        return "/Images/kenny_2.jpg";
    }
}
