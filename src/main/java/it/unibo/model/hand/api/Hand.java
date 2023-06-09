package it.unibo.model.hand.api;

import java.util.List;

import it.unibo.model.army.api.Army;

/**
 * Represents the hand of a player.
 */
public interface Hand {
    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    void addCard(Army card);

    /**
     * Retrieves the hand.
     * 
     * @return the hand
     */
    List<Army> getHand();

    /**
     * Represents the logic for playing cards.
     * 
     * @param cards the list of cards to play
     * @return an integer representing the number of troops obtained
     */
    int playCards(List<Army> cards);

    /**
     * Checks if the given list of cards is playable.
     * 
     * @param cards the list of cards to check
     * @return {@code true} if the cards are playable, {@code false} otherwise
     */
    boolean checkPlayableCards(List<Army> cards);
}
