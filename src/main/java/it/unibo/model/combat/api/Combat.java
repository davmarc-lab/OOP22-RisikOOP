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
         * The striker win fight.
         */
        WIN,

        /**
         * The striker lose fight.
         */
        LOSE,

        /**
         * The combat is invalid.
         */
        NONE
    }

    /**
     * This method start and compute the attack between two territories.
     * 
     * @param numStriker numer of striker's armies
     * @param numDefender numer of defender's armies
     * @return a list of {@code Combat.Results} indicating the combat results 
     */
    List<Results> attack(int numStriker, int numDefender);
}
