package it.unibo.model.gamestate.api;

import it.unibo.model.player.api.Player;

/**
 * Represents the current state of a game.
 */
public interface GameState {

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();

    /**
     * Retrieves the winner of the game.
     *
     * @return the player who won the game
     */
    Player getWinner();
}
