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
    enum Result {
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
     * Enumerating the role of the player during the combat. It defines the troops
     * that have to remain in the terriotory for each role.
     */
    enum Role {
        /**
         * Attacker role.
         * Has to leave at least 1 troop on the territory he attacks from
         */
        ATTACKER(1),

        /**
         * Defender role.
         * Doesn't need to leave any troops on his territory, as he can only try to
         * survive an attack
         */
        DEFENDER(0);

        private final int stableTroops;

        /**
         * @param stableTroops the numbero of troops that have to remain in the
         *                     territory
         */
        Role(final int stableTroops) {
            this.stableTroops = stableTroops;
        }

        /**
         * @return the number of troops that have to remain in the territory
         */
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
    List<Result> attack(int numAttacker, int numDefender);

    /**
     * Checks if a territory has been conquered.
     * 
     * @return true if the territory is conquered, false otherwise
     */
    boolean isTerritoryConquered();

}
