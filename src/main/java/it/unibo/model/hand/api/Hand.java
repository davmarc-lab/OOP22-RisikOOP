package it.unibo.model.hand.api;

import java.util.List;

/**
 * Represents the hand of a player.
 * 
 * @param <T> the type of the hand
 */
public interface Hand<T> {
    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    void addCard(T card);

    /**
     * Gets the hand.
     * 
     * @return the hand
     */
    List<T> getHand();
}
