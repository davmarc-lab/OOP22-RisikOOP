package it.unibo.model.gameloop.api;

import java.util.Iterator;
import java.util.List;

/**
 * This class manages the players and the turn order.
 */
public interface TurnManager {

    /**
     * @return the list of the players' IDs
     */
    List<Integer> getPlayersId();

    /**
     * @return the player that is playing.
     */
    int getCurrentPlayerID();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();

    /**
     * @return the iterator that cycles the list of players' IDs
     */
    Iterator<Integer> getIterator();

}
