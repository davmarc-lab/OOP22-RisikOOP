package it.unibo.model.army.impl;

import it.unibo.model.army.api.Army;

/**
 * Represents an Army card.
 */
public final class ArmyImpl implements Army {

    private final ArmyType armyType;

    /**
     * Creates an Army card.
     * 
     * @param armyType the type of the Army card.
     */
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
