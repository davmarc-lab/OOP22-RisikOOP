package it.unibo.controller.playerhand.api;

import java.util.List;

import it.unibo.model.army.api.Army;

public interface PlayerHandController {

    List<Army> getInputCards();

    void addInputCard(String card);

    void clearInputCards();

    boolean isInputFull();

    boolean isAddPossible(int input, int currentNumber);

    void attemptPlayCards();

    int getNumberOfCards(String cardType);

    int getPlayerFirstCards();

    int getPlayerSecondCards();
    
    int getPlayerThirdCards();
}
