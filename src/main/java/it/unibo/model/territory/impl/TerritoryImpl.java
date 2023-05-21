package it.unibo.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Territory.
 */
public final class TerritoryImpl implements Territory {

    private String name;
    private final Set<Territory> adjTerritories;
    private int numTroops;

    /**
     * Creates a new territory with the name given.
     * 
     * @param name
     *          the territory's name
     */
    public TerritoryImpl(final String name) {
        this.name = name;
        this.adjTerritories = new HashSet<>();
        this.numTroops = 0;
    }

    /**
     * Creates a new territory as a copy of an existing one.
     * 
     * @param t
     *          the territory to copy
     */
    public TerritoryImpl(final Territory t) {
        this(t.getName());
        this.adjTerritories.addAll(t.getAdjTerritories());
        this.numTroops = t.getTroops();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Territory> getAdjTerritories() {
        return Set.copyOf(this.adjTerritories);
    }

    @Override
    public void addAdjTerritory(final Territory t) {
        this.adjTerritories.add(t);
    }

    @Override
    public int getTroops() {
        return this.numTroops;
    }

    @Override
    public void addTroops(final int n) {
        this.numTroops += n;
    }

    @Override
    public Territory getCopyOfTerritory(final Territory t) {
        return new TerritoryImpl(t);
    }

    @Override
    public String toString() {
        return new String(new StringBuilder("NAME = ").append(this.getName()).append(", ADJ = [").append(
                this.getAdjTerritories().stream()
                .map(t -> t.getName())
                .reduce((s1, s2) -> s1 + ", " + s2).get()).append(']'));
    }
}
