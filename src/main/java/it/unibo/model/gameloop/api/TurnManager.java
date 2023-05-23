package it.unibo.model.gameloop.api;

/**
 * This class manages the players and the turn order.
 */
public interface TurnManager {

    /**
     * @return the player that is playing.
     */
    Integer getCurrentPlayerID();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();
}
