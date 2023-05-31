package it.unibo.controller.playerhand.api;

import java.util.List;

import it.unibo.model.army.api.Army.ArmyType;

public interface PlayerHandController {
    
    List<ArmyType> getArmyTypes();

    int getPlayerFirstCards();

    int getPlayerSecondCards();
    
    int getPlayerThirdCards();
}
