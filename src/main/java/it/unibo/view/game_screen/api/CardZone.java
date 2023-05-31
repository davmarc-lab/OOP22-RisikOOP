package it.unibo.view.game_screen.api;

import it.unibo.controller.playerhand.api.PlayerHandController;

public interface CardZone {

    void setController(PlayerHandController controller);

    void updateView();

}
