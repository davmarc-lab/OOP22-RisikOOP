package it.unibo.model.territory.api;

import java.util.Map;
import java.util.Set;

/**
 * Manages all the territories and continents.
 */
public interface GameTerritory {

    /**
     * Retrieves the name of the continent of the given territory.
     * 
     * @param t the territory
     * @return the name of the continent of the given territory
     */
    String getContinentNameFromTerritory(Territory t);

    /**
     * Retrieves the map of all territories divided into continents.
     * 
     * @return the map of the continent and his set of territories
     */
    Map<String, Set<Territory>> getTerritoryMap();

    /**
     * Retrieves the set of all territorry's name.
     * 
     * @return the set of all territory names
     */
    Set<String> getTerritoryNameSet();

    /**
     * Retrieves the set of all territories.
     * 
     * @return the set of all the territories
     */
    Set<Territory> getTerritories();

    /**
     * Retrieves the territory by his name.
     * 
     * @param name the territory name
     * @return the territory whose name is the one given
     */
    Territory getTerritory(String name);

    /**
     * Retrieves the set of all territories of a continent.
     * 
     * @param name the name of the continent
     * @return the set of all territories of the given continent.
     */
    Set<Territory> getTerritoryByContinent(String name);
}
