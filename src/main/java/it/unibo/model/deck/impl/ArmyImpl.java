package it.unibo.model.deck.impl;

import it.unibo.model.deck.api.Army;

public class ArmyImpl implements Army {

    private final ArmyType armyType;

    public ArmyImpl(final ArmyType armyType) {
        this.armyType = armyType;
    }

    @Override
    public ArmyType getArmyType() {
        return armyType;
    }

    @Override
    public String toString() {
        return this.armyType.toString();
    }

}
