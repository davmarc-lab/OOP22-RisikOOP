package it.unibo.model.turns.api;

import java.util.Iterator;
import java.util.List;

/**
 * Manages the players and the turn order.
 */
public interface TurnManager {

    /**
     * Retrieves the list of the players' IDs.
     * 
     * @return the list of the players' IDs
     */
    List<Integer> getPlayersId();

    /**
     * Retrieves the current player ID.
     * 
     * @return the current player ID
     */
    int getCurrentPlayerID();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();

    /**
     * Retrieves the iterator that cycles the list of players' IDs.
     * 
     * @return the iterator that cycles the list of players' IDs
     */
    Iterator<Integer> getIterator();

    /**
     * Restarts the turn cycle.
     */
    void resetTurns();
}
