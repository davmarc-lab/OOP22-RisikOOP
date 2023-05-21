package it.unibo.model.combat.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.dice.api.Dice;
import it.unibo.model.dice.impl.DiceImpl;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Combat interface.
 */
public class CombatImpl implements Combat {

    private static final int MAX_DICE_NUMBER = 6;
    private static final int MAX_ATTACK_DEFEND_ARMY = 3;
    private static final int MIN_ATTACK_DEFEND_ARMY = 1;

    private final List<Integer> strikers = new ArrayList<>();
    private final List<Integer> defenders = new ArrayList<>();
    private final Territory tStriker;
    private final Territory tDefender;
    private final Dice dice = new DiceImpl(MAX_DICE_NUMBER);
    // fields used for test purpose
    private int numberStriker;
    private int numberDefender;
    private boolean testFlag = false;

    /**
     * This constructor create a standard Combat object.
     * 
     * @param tStriker striker's territory
     * @param numberStriker striker's armies
     * @param tDefender defender's territory
     * @param numberDefender defender's armies
     * 
     * {@throws IllegalArgumentException} if the number of armies doesn't respect the rules (must be between 1 and 3)
     */
    public CombatImpl(final Territory tStriker, final int numberStriker,
        final Territory tDefender, final int numberDefender) {
        this(tStriker, tDefender);
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
        this.tStriker = tStriker;
        this.tDefender = tDefender;
    }

    /**
     * This constructor is used for test classes, it creates a situation with default number of armies.
     * and default results of each dice.
     * 
     * @param tStriker striker's territory
     * @param numberStriker striker's armies
     * @param tDefender defender's territory
     * @param numberDefender defender's armies
     * @param strikers results of the dice for striker's armies
     * @param defenders results of the dice for defender's armies
     * @param testFlag flag used in test classes
     */
    public CombatImpl(final Territory tStriker, final int numberStriker, final Territory tDefender,
        final int numberDefender, final List<Integer> strikers, final List<Integer> defenders, final boolean testFlag) {
        this(tStriker, numberStriker, tDefender, numberDefender);
        this.strikers.addAll(strikers);
        this.defenders.addAll(defenders);
        this.testFlag = true;
    }

    /**
     * This method is used to check the number of armies for each side.
     * 
     * @return a {@code boolean} value indicating if the numbers of defenders and strikers are correct
     */
    private boolean isNumberArmiesValid() {
        return this.numberDefender <= MAX_ATTACK_DEFEND_ARMY && this.numberDefender >= MIN_ATTACK_DEFEND_ARMY
            && this.numberStriker <= MAX_ATTACK_DEFEND_ARMY && this.numberStriker >= MIN_ATTACK_DEFEND_ARMY;
    }

    /**
     * Calculate the values of each army throwing a dice.
     * 
     * @param numberOfDice number of armies used in combat
     * @return a {@code List<Integer>} containing the sorted values of each army 
     */
    private List<Integer> declarePower(final int numberOfDice) {
        final List<Integer> l = new ArrayList<>();
        l.addAll(dice.rollMultiple(numberOfDice));
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
        final List<Results> r = new ArrayList<>();
        while (!(strikers.isEmpty() || defenders.isEmpty())) {
            final var s = strikers.get(0);
            final var d = defenders.get(0);
            r.add(s > d ? Results.WIN : Results.LOSE);
            strikers.remove(0);
            defenders.remove(0);
        }
        return r;
    }

    /**
     * This method updates the territories values.
     * 
     * @param list list of combat result
     */
    private void applyCombatResult(final List<Results> list) {
        list.stream().forEach(r -> {
            if (r.equals(Results.WIN)) {
                this.tDefender.addTroops(-1);
            } else if (r.equals(Results.LOSE)) {
                this.tStriker.addTroops(-1);
            } else {
                throw new IllegalCallerException("Invalid combat resutl, aborted operation");
            }
        });
    }

    /**
     * This method checks the validity of the combat.
     * 
     * @return {@code true} if the combat is valid
     */
    private boolean checkAttackValidity() {
        return this.tStriker.getAdjTerritories().contains(this.tDefender)
            && this.tStriker.getTroops() > 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Results> attack(final int numStriker, final int numDefender) {
        this.numberStriker = numStriker;
        this.numberDefender = numDefender;

        if (!isNumberArmiesValid()) {
            throw new IllegalArgumentException("The number of armies cannot be less or equal 0 or more than 3");
        }

        // only for test purpose
        if (!checkAttackValidity()) {
            return List.of(Results.NONE);
        }

        // only for test purpose
        if (testFlag) {
            var res = this.computeAttack(strikers, defenders);
            applyCombatResult(res);
            return res;
        }

        final var strikers = declarePower(this.numberStriker);
        final var defenders = declarePower(this.numberDefender);
        final var results = computeAttack(strikers, defenders);
        // removing armies from the territories
        applyCombatResult(results);
        return results;
    }
}
