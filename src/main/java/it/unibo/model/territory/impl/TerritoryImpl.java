package it.unibo.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Territory.
 */
public final class TerritoryImpl implements Territory {

    private String name;
    private Set<Territory> adjTerritories;
    private int numArmy;

    /**
     * Creates a new territory with the name given.
     * 
     * @param name
     *          the territory name
     */
    public TerritoryImpl(final String name) {
        this.name = name;
        this.adjTerritories = new HashSet<>();
        this.numArmy = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Territory> getAdjTerritories() {
        return this.adjTerritories;
    }

    @Override
    public void addAdjTerritory(final Territory t) {
        this.adjTerritories.add(t);
    }

    @Override
    public int getArmy() {
        return this.numArmy;
    }

    @Override
    public void addArmy(final int n) {
        this.numArmy += n;
    }

    @Override
    public String toString() {
        return new String(new StringBuilder("NAME = ").append(this.getName()).append("NUM_ARMY = ").append(this.getArmy())
                .append(", ADJ = [").append(
                this.getAdjTerritories().stream()
                .map(t -> t.getName())
                .reduce((s1, s2) -> s1 + ", " + s2).get()).append("]"));
    }
}
