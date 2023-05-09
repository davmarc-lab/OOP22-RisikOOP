package it.unibo.model.deck.api;

public interface Army {

    enum ArmyType {
        INFANTRY, CAVALRY, ARTILLERY
    }

    ArmyType getArmyType();

    String toString();

}
