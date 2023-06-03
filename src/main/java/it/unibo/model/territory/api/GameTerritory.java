package it.unibo.model.territory.api;

import java.util.Map;
import java.util.Set;

/**
 * This interface models a class that manages all the territories and
 * continents.
 */
public interface GameTerritory {

    /**
     * Gets the name of the continent of the given territory.
     * 
     * @param t
     *          the territory
     * @return the name of the continent of the given territory
     */
    String getContinentNameFromTerritory(Territory t);

    /**
     * Gets the map of all territories divided into continents.
     * 
     * @return the map of the continent and his set of territories
     */
    Map<String, Set<Territory>> getTerritoryMap();

    /**
     * Gets the set of all territorry's name.
     * 
     * @return the set of all territory names
     */
    Set<String> getTerritoryNameSet();

    /**
     * Gets the set of all territories.
     * 
     * @return the set of all the territories
     */
    Set<Territory> getTerritories();

    /**
     * Gets the territory by his name.
     * 
     * @param name
     *             the territory name
     * @return the territory whose name is the one given
     */
    Territory getTerritory(String name);

    /**
     * Gets the set of all territories of a continent.
     * 
     * @param name
     *             the name of the continent
     * @return the set of all territories of the given continent.
     */
    Set<Territory> getTerritoryByContinent(String name);

}
