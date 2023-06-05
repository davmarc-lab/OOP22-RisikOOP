package it.unibo.model.army.impl;

import it.unibo.model.army.api.Army;

/**
 * Implementation of {@link Army}.
 * Represents an Army card.
 */
public class ArmyImpl implements Army {

    private final ArmyType armyType;

    /**
     * Creates an Army card.
     * 
     * @param armyType the type of the Army card.
     */
    public ArmyImpl(final ArmyType armyType) {
        this.armyType = armyType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArmyType getArmyType() {
        return armyType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.armyType.toString();
    }
}
