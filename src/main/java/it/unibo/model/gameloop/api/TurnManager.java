package it.unibo.model.gameloop.api;

import java.util.Iterator;
import java.util.List;

/**
 * This class manages the players and the turn order.
 */
public interface TurnManager {

    List<Integer> getPlayersId();

    /**
     * @return the player that is playing.
     */
    int getCurrentPlayerID();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();

    Iterator<Integer> getIterator();
}
