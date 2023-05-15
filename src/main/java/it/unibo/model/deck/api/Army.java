package it.unibo.model.deck.api;

/**
 * Represents an Army card.
 */
public interface Army {
    /**
     * Represents the type of the Army card.
     */
    enum ArmyType {
        /**
         * Represents the Infantry Army card.
         */
        INFANTRY,
        /**
         * Represents the Cavalry Army card.
         */
        CAVALRY,
        /**
         * Represents the Artillery Army card.
         */
        ARTILLERY
    }

    /**
     * Gets the type of the Army card.
     * 
     * @return the type of the Army card.
     */
    ArmyType getArmyType();

    /**
     * Gets the string representation of the Army card.
     * 
     * @return the string representation of the Army card.
     */
    @Override
    String toString();

}
