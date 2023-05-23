package it.unibo.model.hand.impl;

import java.util.List;

import it.unibo.model.hand.api.Hand;

public abstract class HandImpl<T> implements Hand<T> {

    private final List<T> hand;

    public HandImpl(final List<T> hand) {
        this.hand = hand;
    }

    @Override
    public void addCard(final T card) {
        this.hand.add(card);
    }

    @Override
    public List<T> getHand() {
        return List.copyOf(this.hand);
    }

    protected abstract int playCards(final List<T> cards);

    protected abstract boolean checkPlayableCards(final List<T> cards);

}
