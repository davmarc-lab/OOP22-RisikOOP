package it.unibo.model.deck.api;

import java.util.LinkedList;

public interface Deck<T> {

    void addCard(T card);

    T drawCard();

    void shuffle();

    LinkedList<T> getDeck();

    String toString();

}
