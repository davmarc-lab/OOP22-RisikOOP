package it.unibo.model.territory.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of {@link GameTerritory} interface.
 * It manages the territories created by the factory.
 */
public class GameTerritoryImpl implements GameTerritory {

    private final Map<String, Set<Territory>> territories;

    /**
     * Creates a copy of the territories given and operates on them.
     * 
     * @param t the map of string and set of territories
     */
    public GameTerritoryImpl(final Map<String, Set<Territory>> t) {
        this.territories = new HashMap<>(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContinentNameFromTerritory(final Territory t) {
        return this.territories.entrySet().stream().filter(x -> x.getValue().contains(t)).findFirst().get().getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Set<Territory>> getTerritoryMap() {
        return Map.copyOf(this.territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getTerritoryNameSet() {
        return this.getTerritories().stream().map(Territory::getName).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getTerritories() {
        final Set<Territory> set = new HashSet<>();
        this.territories.values().stream().forEach(s -> set.addAll(s));
        return set;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getTerritory(final String name) {
        return this.getTerritories().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getTerritoryByContinent(final String name) {
        return this.getTerritoryMap().get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getTerritories().toString();
    }
}
