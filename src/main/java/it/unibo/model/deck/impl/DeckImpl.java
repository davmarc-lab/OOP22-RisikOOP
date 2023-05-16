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
public final class DeckImpl<T> implements Deck<T> {

    private final List<T> deck = new ArrayList<>();

    /**
     * Creates a deck of cards.
     *
     * @param cards the cards to be added to the deck
     */
    public DeckImpl(final Collection<T> cards) {
        this.deck.addAll(cards);
    }

    @Override
    public void addCard(final T card) {
        this.deck.add(card);
    }

    @Override
    public T drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        return this.deck.remove(0);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    @Override
    public List<T> getDeck() {
        return Collections.unmodifiableList(this.deck);
    }
}
