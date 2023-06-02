package it.unibo.model.combat.api;

import java.util.List;

/**
 * This interface is used to istance a combat between two player.
 */
public interface Combat {

    /**
     * This enum is used for calculating the comabt between two dices.
     * The possible values are:
     * {@link #WIN},
     * {@link #LOSE},
     * {@link #NONE}
     */
    enum Results {
        /**
         * The attacker win fight.
         */
        WIN,

        /**
         * The attacker lose fight.
         */
        LOSE,

        /**
         * The combat is invalid.
         */
        NONE
    }

    /**
     * Enumerating the role of the player during the combat. It defined the troops
     * that have to remain in the terriotory for each role.
     */
    enum Role {
        ATTACKER(1),
        DEFENDER(0);

        final int stableTroops;

        private Role(final int stableTroops) {
            this.stableTroops = stableTroops;
        }

        public int getStableTroops() {
            return this.stableTroops;
        }

    }

    /**
     * This method start and compute the attack between two territories.
     * 
     * @param numAttacker numer of attacker's armies
     * @param numDefender numer of defender's armies
     * @return a list of {@code Combat.Results} indicating the combat results
     */
    List<Results> attack(int numAttacker, int numDefender);

    boolean isTerritoryConquered();
}
