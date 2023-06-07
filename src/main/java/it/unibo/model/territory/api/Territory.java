package it.unibo.model.territory.api;

import java.util.Set;

/**
 * Defines a Territory.
 */
public interface Territory {

    /**
     * Retrieves territory's name.
     * 
     * @return territory's name
     */
    String getName();

    /**
     * Retrieves the set of all territory's adjacent territories.
     * 
     * @return adjacent territories' set
     */
    Set<Territory> getAdjTerritories();

    /**
     * Add a territory to the set of the adjacents.
     * 
     * @param t the territory
     */
    void addAdjTerritory(Territory t);

    /**
     * Retrieves the number of troops in the territory.
     * 
     * @return the number of troops in the territory
     */
    int getTroops();

    /**
     * Add the given number of troops.
     * 
     * @param n number of troops to add
     */
    void addTroops(int n);
}
