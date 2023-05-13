package it.unibo.model.deck.impl;

import it.unibo.model.deck.api.Army;
/**
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
     * Gets the type of the Army card.
     * 
     * @return the type of the Army card.
     */
    @Override
    public ArmyType getArmyType() {
        return armyType;
    }
    /**
     * Gets the string representation of the Army card.
     * 
     * @return the string representation of the Army card.
     */
    @Override
    public String toString() {
        return this.armyType.toString();
    }

}
