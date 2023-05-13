package it.unibo.model.deck.api;

import java.util.List;
/**
 * Represents a generic deck of cards.
 * 
 * @param <T> the type of cards in the deck.
 */
public interface Deck<T> {
    /**
     * Adds a card to the deck.
     * 
     * @param card the card to be added
     */
    void addCard(T card);
    /**
     * Draws a card from the deck.
     * 
     * @return the card drawn
     */
    T drawCard();
    /**
     * Shuffles the deck.
     */
    void shuffle();
    /**
     * Gets the deck.
     * 
     * @return the deck
     */
    List<T> getDeck();
}
