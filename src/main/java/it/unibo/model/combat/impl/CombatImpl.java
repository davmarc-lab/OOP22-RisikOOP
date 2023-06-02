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

    private final List<Integer> attackers = new ArrayList<>();
    private final List<Integer> defenders = new ArrayList<>();
    private final Territory tAttacker;
    private final Territory tDefender;
    private final Dice dice = new DiceImpl(MAX_DICE_NUMBER);
    // fields used for test purpose
    private int numberAttacker;
    private int numberDefender;
    private boolean testFlag;

    /**
     * This constructor create a standard Combat object.
     * 
     * @param tAttacker      attacker's territory
     * @param numberAttacker attacker's armies
     * @param tDefender      defender's territory
     * @param numberDefender defender's armies
     * 
     * {
     * @throws IllegalArgumentException} if the number of armies doesn't respect the
     *                                   rules (must be between 1 and 3)
     */
    public CombatImpl(final Territory tAttacker, final int numberAttacker,
            final Territory tDefender, final int numberDefender) {
        this(tAttacker, tDefender);
        this.numberAttacker = numberAttacker;
        this.numberDefender = numberDefender;
    }

    /**
     * This constructor is used for test classes creating a situation with 0
     * attackers and defenders.
     * 
     * @param tAttacker attacker's territories
     * @param tDefender defender's territories
     */
    public CombatImpl(final Territory tAttacker, final Territory tDefender) {
        this.tAttacker = tAttacker;
        this.tDefender = tDefender;
    }

    /**
     * This constructor is used for test classes, it creates a situation with
     * default number of armies.
     * and default results of each dice.
     * 
     * @param tAttacker      attacker's territory
     * @param numberAttacker attacker's armies
     * @param tDefender      defender's territory
     * @param numberDefender defender's armies
     * @param attackers      results of the dice for attacker's armies
     * @param defenders      results of the dice for defender's armies
     * @param testFlag       flag used in test classes
     */
    public CombatImpl(final Territory tAttacker, final int numberAttacker, final Territory tDefender,
            final int numberDefender, final List<Integer> attackers, final List<Integer> defenders,
            final boolean testFlag) {
        this(tAttacker, numberAttacker, tDefender, numberDefender);
        this.attackers.addAll(attackers);
        this.defenders.addAll(defenders);
        this.testFlag = testFlag;
    }

    /**
     * This method is used to check the number of armies for each side.
     * 
     * @return a {@code boolean} value indicating if the numbers of defenders and
     *         attackers are correct
     */
    private boolean isNumberArmiesValid() {
        return this.numberDefender <= MAX_ATTACK_DEFEND_ARMY && this.numberDefender >= MIN_ATTACK_DEFEND_ARMY
                && this.numberAttacker <= MAX_ATTACK_DEFEND_ARMY && this.numberAttacker >= MIN_ATTACK_DEFEND_ARMY;
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
        l.sort((x, y) -> y - x);
        return l;
    }

    /**
     * Calculate the result of the combat comparing the values obtained.
     * 
     * @param attackers values of each attacker army
     * @param defenders values of each defender army
     * @return a {@code List<Results>} containing the result of each fight between
     *         armies
     */
    private List<Result> computeAttack(final List<Integer> attackers, final List<Integer> defenders) {
        final List<Result> r = new ArrayList<>();
        while (!(attackers.isEmpty() || defenders.isEmpty())) {
            final var s = attackers.get(0);
            final var d = defenders.get(0);
            r.add(s > d ? Result.WIN : Result.LOSE);
            attackers.remove(0);
            defenders.remove(0);
        }
        return r;
    }

    /**
     * This method updates the territories values.
     * 
     * @param list list of combat result
     */
    private void applyCombatResult(final List<Result> list) {
        list.stream().forEach(r -> {
            if (r.equals(Result.WIN)) {
                this.tDefender.addTroops(-1);
            } else if (r.equals(Result.LOSE)) {
                this.tAttacker.addTroops(-1);
            } else {
                throw new IllegalCallerException("Invalid combat result, aborted operation");
            }
        });
    }

    /**
     * This method checks the validity of the combat.
     * 
     * @return {@code true} if the combat is valid
     */
    private boolean checkAttackValidity() {
        return this.tAttacker.getAdjTerritories().contains(this.tDefender)
                && this.tAttacker.getTroops() > 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Result> attack(final int numAttacker, final int numDefender) {
        this.numberAttacker = numAttacker;
        this.numberDefender = numDefender;

        if (!isNumberArmiesValid()) {
            throw new IllegalArgumentException("The number of armies cannot be less or equal 0 or more than 3");
        }

        // only for test purpose
        if (!checkAttackValidity()) {
            return List.of(Result.NONE);
        }

        // only for test purpose
        if (testFlag) {
            final List<Result> res = this.computeAttack(attackers, defenders);
            applyCombatResult(res);
            return res;
        }

        final var attackers = declarePower(this.numberAttacker);
        final var defenders = declarePower(this.numberDefender);
        final var results = computeAttack(attackers, defenders);
        // removing armies from the territories
        applyCombatResult(results);
        return results;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTerritoryConquered() {
        return this.tDefender.getTroops() == 0;
    }

}
