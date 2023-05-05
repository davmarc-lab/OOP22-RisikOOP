package it.unibo.model.territory.api;

import java.util.Set;

public interface TerritoryFactory {

    void createTerritorySet();

    Set<Territory> getTerritories();

    Set<String> getNameSet();

    Territory getTerritory(String name);
}
