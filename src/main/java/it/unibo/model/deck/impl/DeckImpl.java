package it.unibo.model.deck.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.unibo.model.deck.api.Deck;

/**
 * Implementation of {@link Deck} interface.
 * 
 * Represents a generic deck of cards.
 *
 * @param <T> the type of cards in the deck.
 */
public class DeckImpl<T> implements Deck<T> {

    private final List<T> deck;

    /**
     * Constructor that creates a deck of cards.
     *
     * @param cards the cards to be added to the deck
     */
    public DeckImpl(final Collection<T> cards) {
        this.deck = new ArrayList<>(cards);
    }

    /**
     * Constructor that creates an empty deck.
     */
    public DeckImpl() {
        this.deck = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final T card) {
        this.deck.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCard(final T card) {
        this.deck.remove(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T drawCard() {
        if (this.deck.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        return this.deck.remove(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getDeck() {
        return new ArrayList<>(this.deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeck(final Collection<T> deck) {
        this.deck.clear();
        this.deck.addAll(deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Deck = [" + deck + "]\n";
    }
}
