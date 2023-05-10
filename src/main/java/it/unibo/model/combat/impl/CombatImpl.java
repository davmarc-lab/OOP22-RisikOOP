package it.unibo.model.combat.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Combat interface.
 */
public final class CombatImpl implements Combat {

    private static final int MAX_DICE_NUMBER = 6;
    private static final int MAX_ATTACK_DEFEND_ARMY = 3;
    private static final int MIN_ATTACK_DEFEND_ARMY = 1;

    private final List<Integer> strikers = new ArrayList<>();       // used for testing purpose
    private final List<Integer> defenders = new ArrayList<>();      // used for testing purpose
    private final Territory tStriker;
    private final Territory tDefender;
    private final int numberStriker;
    private final int numberDefender;

    /**
     * This constructor create a standard Combat object.
     * 
     * @param tStriker striker's territory
     * @param numberStriker striker's armies
     * @param tDefender defender's territory
     * @param numberDefender defender's armies
     */
    public CombatImpl(final Territory tStriker, final int numberStriker,
        final Territory tDefender, final int numberDefender) {
        this.tStriker  = tStriker;
        this.tDefender = tDefender;
        if (!isNumberArmiesValid()) {
            throw new IllegalArgumentException("The number of armies cannot be less or equal 0 or more then 3");
        }
        this.numberStriker = numberStriker;
        this.numberDefender = numberDefender;
    }

    /**
     * This constructor is used for test classes creating a situation with 0 strikers and defenders.
     * 
     * @param tStriker striker's territories
     * @param tDefender defender's territories
     */
    public CombatImpl(final Territory tStriker, final Territory tDefender) {
        this(tStriker, 0, tDefender, 0);
    }

    /**
     * This constructor is used for test classes, it creates a situation with default number of armies.
     * and default results of each dice
     * 
     * @param tStriker striker's territory
     * @param numberStriker striker's armies
     * @param tDefender defender's territory
     * @param numberDefender defender's armies
     * @param strikers results of the dice for striker's armies
     * @param defenders results of the dice for defender's armies
     */
    public CombatImpl(final Territory tStriker, final int numberStriker, final Territory tDefender,
        final int numberDefender, final List<Integer> strikers, final List<Integer> defenders) {
        this(tStriker, numberStriker, tDefender, numberDefender);
        this.strikers.addAll(strikers);
        this.defenders.addAll(defenders);
    }

    /**
     * This method is used to check the number of armies for each side.
     * 
     * @return a {@code boolean} value indicating if the numbers of defenders and strikers are correct
     */
    private boolean isNumberArmiesValid() {
        return numberDefender <= MAX_ATTACK_DEFEND_ARMY && numberDefender >= MIN_ATTACK_DEFEND_ARMY
            || numberStriker <= MAX_ATTACK_DEFEND_ARMY || numberStriker >= MIN_ATTACK_DEFEND_ARMY;
    }

    /**
     * Simulate the behavior of a dice, giving a random number between 1 and 6.
     * 
     * @return a random dice number
     */
    private int rollDice() {
        return new Random().nextInt(MAX_DICE_NUMBER) + 1;
    }

    /**
     * Calculate the values of each army throwing a dice.
     * 
     * @param numberOfDice number of armies used in combat
     * @return a {@code List<Integer>} containing the sorted values of each army 
     */
    private List<Integer> declarePower(final int numberOfDice) {
        final List<Integer> l = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            l.add(rollDice());
        }
        l.sort((x, y) ->  y - x);
        return l;
    }

    /**
     * Calculate the result of the combat comparing the values obtained.
     * 
     * @param strikers values of each striker army
     * @param defenders values of each defender army
     * @return a {@code List<Results>} containing the result of each fight between armies
     */
    private List<Results> computeAttack(final List<Integer> strikers, final List<Integer> defenders) {
        List<Results> r = new ArrayList<>();
        while (!(strikers.isEmpty() || defenders.isEmpty())) {
            var s = strikers.get(0);
            var d = defenders.get(0);
            r.add((s > d ? Results.WIN : Results.LOSE));
            strikers.remove(0);
            defenders.remove(0);
        }
        return r;
    }

    @Override
    public List<Results> attack() {
        var strikers = declarePower(numberStriker);
        var defenders = declarePower(numberDefender);
        System.out.println(strikers + "\n" + defenders);
        var r = computeAttack(strikers, defenders);
        System.out.println(r);

        // removing armies from the territories
        for (var x: r) {
            if (x.equals(Combat.Results.WIN)) {
                tDefender.addArmy(-1);
            } else if (x.equals(Combat.Results.LOSE)) {
                tStriker.addArmy(-1);
            }
        }
        return r;
    }
}
