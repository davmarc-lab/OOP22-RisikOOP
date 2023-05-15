package it.unibo.model.deck.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.unibo.model.deck.api.Deck;

/**
 * Represents a generic deck of cards.
 *
 * @param <T> the type of cards in the deck.
 */
public class DeckImpl<T> implements Deck<T> {

    private final List<T> deck = new ArrayList<>();

    /**
     * Creates a deck of cards.
     *
     * @param cards the cards to be added to the deck
     */
    public DeckImpl(final Collection<T> cards) {
        this.deck.addAll(cards);
    }

    /**
     * Adds a card to the deck.
     *
     * @param card the card to be added
     */
    @Override
    public void addCard(final T card) {
        this.deck.add(card);
    }

    /**
     * Draws a card from the deck.
     *
     * @return the card drawn
     */
    @Override
    public T drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        return this.deck.remove(0);
    }

    /**
     * Shuffles the deck.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * Gets the deck.
     *
     * @return the deck
     */
    @Override
    public List<T> getDeck() {
        return Collections.unmodifiableList(this.deck);
    }
}
