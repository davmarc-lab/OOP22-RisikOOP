package it.unibo.model.combat.api;

import it.unibo.common.Pair;
import it.unibo.model.territory.api.Territory;

/**
 * Provides methods to instance a combat
 * between two players and enums to check the results and
 * roles of each player.
 */
public interface Combat {

    /**
     * Used to calculate the combat between two dices.
     * The possible values are:
     * {@link #WIN},
     * {@link #LOSE},
     * {@link #NONE}
     */
    enum Result {
        /**
         * The attacker wins fight.
         */
        WIN,

        /**
         * The attacker loses fight.
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
         * Has to leave at least 1 troop on the territory he attacks from.
         */
        ATTACKER(1),

        /**
         * Defender role.
         * Doesn't need to leave any troops on his territory, as he can only try to
         * survive an attack.
         */
        DEFENDER(0);

        private final int stableTroops;

        /**
         * @param stableTroops the number of troops that have to remain in the
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
     * Starts and compute the attack between two territories.
     * 
     * @param numAttacker number of attacker's troops
     * @param numDefender number of defender's troops
     * @return a {@code Pair<Integer, Integer>} containing the number of lost troops
     *         by attacker and defender
     */
    Pair<Integer, Integer> attack(int numAttacker, int numDefender);

    /**
     * Checks if a territory has been conquered.
     * 
     * @param defender the defender territory
     * @return {@code true} if the territory is conquered, {@code false} otherwise
     */
    boolean isTerritoryConquered(Territory defender);
}
