package it.unibo.model.territory.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.controller.JsonReaderTerritory;

import it.unibo.common.Pair;
/**
 * Implementation of the territory factory.
 */
public final class TerritoryFactoryImpl implements TerritoryFactory {

    private Map<String, Set<Territory>> territories;
    private final JsonReaderTerritory readerTerritory;

    /**
     * Creates the map of all territories from the default file.
     */
    public TerritoryFactoryImpl() {
        this.readerTerritory = new JsonReaderTerritory();
        this.territories = new HashMap<>();
    }

    @Override
    public void createTerritories() {
        List<Pair<String, Set<Territory>>> list = this.readerTerritory.readFromJSON();
        list.forEach(p -> this.territories.put(p.getX(), p.getY()));
    }

    @Override
    public String getContinentNameFromTerritory(final Territory t) {
        return this.territories.entrySet().stream().filter(x -> x.getValue().contains(t)).findFirst().get().getKey();
    }

    @Override
    public Map<String, Set<Territory>> getTerritoryMap() {
        return Map.copyOf(this.territories);
    }

    @Override
    public Set<String> getTerritoryNameSet() {
        return this.getTerritories().stream().map(t -> t.getName()).collect(Collectors.toSet());
    }

    @Override
    public Set<Territory> getTerritories() {
        final Set<Territory> set = new HashSet<>();
        this.territories.values().stream().forEach(s -> set.addAll(s));
        return set;
    }

    @Override
    public Territory getTerritory(final String name) {
        return this.getTerritories().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().get();
    }

    @Override
    public Set<Territory> getTerritoryByContinent(final String name) {
        return this.getTerritoryMap().get(name);
    }

    @Override
    public String toString() {
        return this.getTerritories().toString();
    }
}
