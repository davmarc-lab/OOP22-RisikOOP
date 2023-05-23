package it.unibo.model.hand.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.hand.api.Hand;

public class HandImpl<T> implements Hand<T> {

    private final int PLAYABE_CARDS = 3;
    private final List<T> hand = new ArrayList<>();

    @Override
    public void addCard(final T card) {
        this.hand.add(card);
    }

    @Override
    public boolean checkPlayableCards(final List<T> cards) {
        return this.hand.size() == PLAYABE_CARDS && this.hand.containsAll(cards);
    }

    @Override
    public List<T> getHand() {
        return List.copyOf(this.hand);
    }
}
