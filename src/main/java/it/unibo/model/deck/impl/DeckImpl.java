package it.unibo.model.deck.impl;

import java.util.Collections;
import java.util.LinkedList;

import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;

public class DeckImpl<T> implements Deck<T> {

    private LinkedList<T> deck = new LinkedList<>();

    @Override
    public void addCard(final T card) {
        this.deck.add(card);
    }

    @Override
    public T drawCard() {
        return this.deck.removeFirst();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    @Override
    public LinkedList<T> getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return this.deck.toString();
    }
    
    public static void main(String[] args) {
        ObjectiveFactoryImpl ofi = new ObjectiveFactoryImpl();
        Deck<Objective> deck = new DeckImpl<>();
        ofi.createObjectiveSet();
        for (Objective t : ofi.getSetObjectives()) {
            deck.addCard(t);
        }
        deck.shuffle();
        Objective drawnObjective = deck.drawCard();
        System.out.println(drawnObjective);
    }
}
