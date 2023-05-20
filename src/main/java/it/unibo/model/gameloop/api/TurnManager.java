package it.unibo.model.gameloop.api;

import it.unibo.model.player.api.Player;

/**
 * This class manages the players and the turn order.
 */
public interface TurnManager {

    /**
     * @return the player that is playing.
     */
    Player getCurrentPlayer();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();
}
