package it.unibo.model.deck.api;

import java.util.Collection;
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
     * Removes a card from the {@code Deck}.
     * 
     * @param card the card to be removed
     */
    void removeCard(T card);

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
     * Retrieves the deck.
     * 
     * @return the deck
     */
    List<T> getDeck();

    /**
     * Sets the deck.
     * 
     * @param deck the deck to set
     */
    void setDeck(Collection<T> deck);
}
