package it.unibo.model.dice.api;

import java.util.List;

/**
 * Interface representing a dice.
 */
public interface Dice {

    /**
     * Rolls the dice and returns the result.
     *
     * @return the result of the dice roll.
     */
    int roll();

    /**
     * Rolls multiple dice and returns the results in a {@code List}.
     *
     * @param numDice the number of dice to roll.
     * @return a list of results from rolling the dice.
     */
    List<Integer> rollMultiple(int numDice);
}
