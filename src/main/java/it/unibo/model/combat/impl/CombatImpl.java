package it.unibo.model.combat.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.territory.api.Territory;

public class CombatImpl implements Combat {

    private final Territory tStriker;
    private final Territory tDefender;
    private final int numberStriker;        //cannot be <= 0
    private final int numberDefender;       //cannot be <= 0

    public CombatImpl(final Territory tStriker, final int numberStriker, final Territory tDefender, final int numberDefender) {
        this.tStriker  = tStriker;
        this.tDefender = tDefender;
        this.numberStriker = numberStriker;
        this.numberDefender = numberDefender;
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
            if (s > d) {
                r.add(Results.WIN);
            } else {
                r.add(Results.LOSE);
            }
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
        return r;
    }

    @Override
    public String toString() {
        return new String("Dice -> " + rollDice());
    }
    
}
