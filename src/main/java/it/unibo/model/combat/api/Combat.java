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
     * @return a list of Combat.Results indicating the combat results 
     */
    List<Results> attack(final int numStriker, final int numDefender);
}
