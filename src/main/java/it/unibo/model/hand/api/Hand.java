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

    /**
     * Abstract method representing the logic for playing cards.
     * Subclasses should implement this method to define the gameplay.
     * 
     * @param cards the list of cards to play
     * @return an integer representing the outcome of the gameplay
     */
    abstract int playCards(List<T> cards);

    /**
     * Abstract method for checking if the given list of cards is playable.
     * Subclasses should implement this method to define the playable card logic.
     * 
     * @param cards the list of cards to check
     * @return true if the cards are playable, false otherwise
     */
    abstract boolean checkPlayableCards(List<T> cards);
}
