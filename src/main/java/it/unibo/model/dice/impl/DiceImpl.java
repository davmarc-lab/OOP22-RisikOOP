package it.unibo.model.dice.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.model.dice.api.Dice;

/**
 * Implementation of the Dice interface.
 */
public final class DiceImpl implements Dice {
    private final int numFaces;
    private int currentValue;
    private final Random random;

    /**
     * Creates a dice with the specified number of faces.
     *
     * @param numFaces the number of faces on the dice.
     */
    public DiceImpl(final int numFaces) {
        this.numFaces = numFaces;
        this.random = new Random();
        this.currentValue = 1;
    }

    @Override
    public int roll() {
        this.currentValue = random.nextInt(this.numFaces) + 1;
        return this.currentValue;
    }

    @Override
    public List<Integer> rollMultiple(final int numDice) {
        return IntStream.range(0, numDice)
                .mapToObj(i -> this.roll())
                .collect(Collectors.toList());
    }
}
