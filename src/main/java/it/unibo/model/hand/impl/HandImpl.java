package it.unibo.model.hand.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.hand.api.Hand;

/**
 * Implementation of the {@link Hand} interface representing the hand of a
 * player.
 * 
 * @param <T> the type of the hand
 */
public abstract class HandImpl<T> implements Hand<T> {

    private final List<T> hand;

    /**
     * Constructs a HandImpl object with the specified initial hand.
     * 
     * @param hand the initial hand
     */
    public HandImpl(final List<T> hand) {
        this.hand = hand;
    }

    /**
     * Constructs a HandImpl object with an empty hand.
     */
    public HandImpl() {
        this.hand = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final T card) {
        this.hand.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getHand() {
        return this.hand;
    }
}
