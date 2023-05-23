package it.unibo.model.hand.api;

import java.util.List;

public interface Hand<T> {

    void addCard(T card);

    boolean checkPlayableCards(List<T> cards);

    List<T> getHand();

}
