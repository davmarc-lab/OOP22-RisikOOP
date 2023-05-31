package it.unibo.controller.playerhand.api;

import java.util.List;

import it.unibo.model.army.api.Army.ArmyType;

public interface PlayerHandController {

    List<ArmyType> getInputCards();

    void addInputCard(final ArmyType card);

    void clearInputCards();

    ArmyType getArmyTypeFromString(final String name);

    int getPlayerFirstCards();

    int getPlayerSecondCards();
    
    int getPlayerThirdCards();
}
