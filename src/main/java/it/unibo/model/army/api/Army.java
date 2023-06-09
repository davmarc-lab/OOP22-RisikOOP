package it.unibo.model.army.api;

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
        INFANTRY("Infantry"),

        /**
         * Represents the Cavalry Army card.
         */
        CAVALRY("Cavalry"),

        /**
         * Represents the Artillery Army card.
         */
        ARTILLERY("Artillery");

        private final String name;

        ArmyType(final String name) {
            this.name = name;
        }

        /**
         * Retrieves the name of the type.
         * 
         * @return the name of the type
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * Retrieves the type of the Army card.
     * 
     * @return the type of the Army card.
     */
    ArmyType getArmyType();
}
