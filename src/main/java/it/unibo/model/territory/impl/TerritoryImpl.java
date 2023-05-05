package it.unibo.model.territory.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.territory.api.Territory;

public class TerritoryImpl implements Territory {

    private String name;
    private Set<Territory> adjTerritories;

    public TerritoryImpl(final String name) {
        this.name = name;
        this.adjTerritories = new HashSet<>();
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
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        for (final Territory t: this.getAdjTerritories()) {
            sBuilder.append(t.getName()).append(", ");
        }
        sBuilder.delete(sBuilder.length() - 2, sBuilder.length() - 1);
        String s = new String(new StringBuilder("NAME = ").append(this.getName()).append(", ADJ = [").append(sBuilder.toString()).append("]"));

        return s;
    }
}
