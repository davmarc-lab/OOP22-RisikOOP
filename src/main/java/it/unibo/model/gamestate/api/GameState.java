package it.unibo.model.gamestate.api;

import it.unibo.model.player.api.Player;

public interface GameState {

    boolean isGameFinished();

    Player getWinner();

}
