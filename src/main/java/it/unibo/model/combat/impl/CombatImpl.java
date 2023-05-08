package it.unibo.model.combat.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.territory.api.Territory;

public class CombatImpl implements Combat {

    private static final int MAX_ATTACK_DEFEND_ARMY = 3;
    private static final int MIN_ATTACK_DEFEND_ARMY = 1;

    private final List<Integer> strikers = new ArrayList<>();       // used for testing purpose
    private final List<Integer> defenders = new ArrayList<>();      // used for testing purpose
    private final Territory tStriker;
    private final Territory tDefender;
    private final int numberStriker;
    private final int numberDefender;

    private boolean isNumberArmiesValid() {
        return numberDefender <= MAX_ATTACK_DEFEND_ARMY && numberDefender >= MIN_ATTACK_DEFEND_ARMY ||
            numberStriker <= MAX_ATTACK_DEFEND_ARMY || numberStriker >= MIN_ATTACK_DEFEND_ARMY;
    }

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

    public CombatImpl(final Territory tStriker, final Territory tDefender) {
        this(tStriker, 0, tDefender, 0);
    }

    public CombatImpl(final Territory tStriker, final int numberStriker, final Territory tDefender,
        final int numberDefender, final List<Integer> strikers, final List<Integer> defenders) {
        this(tStriker, numberStriker, tDefender, numberDefender);
        this.strikers.addAll(strikers);
        this.defenders.addAll(defenders);
    }

    private int rollDice() {
        return new Random().nextInt(6) + 1;
    }

    private List<Integer> declarePower(final int numberOfDice) {
        final List<Integer> l = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            l.add(rollDice());
        }
        l.sort((x, y) ->  y - x);
        return l;
    }

    private List<Results> computeAttack(final List<Integer> strikers, final List<Integer> defenders) {
        List<Results> r = new ArrayList<>();
        while(!(strikers.isEmpty() || defenders.isEmpty())) {
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

    @Override
    public String toString() {
        return new String("Dice -> " + rollDice());
    }
    
}
