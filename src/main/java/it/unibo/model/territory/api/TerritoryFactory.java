package it.unibo.model.territory.api;

import java.util.Set;

/**
 * 
 */
public interface TerritoryFactory {

    /**
     * Creates the set of all territories.
     */
    void createTerritorySet();

    /**
     * 
     * @return the set of all territories
     */
    Set<Territory> getTerritories();

    /**
     * 
     * @return the set of all territory names
     */
    Set<String> getNameSet();

    /**
     * 
     * @param name
     *          the territory name
     * @return the territory whose name is the one given
     */
    Territory getTerritory(String name);
}
