package it.unibo.model.territory.api;

import java.util.Set;

/**
 * Defines a Territory.
 */
public interface Territory {

    /**
     * Gets territory's name.
     * 
     * @return territory's name
     */
    String getName();

    /**
     * Gets the set of all territory's adjacent territories.
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
     * Gets the number of troops in the territory.
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

    /**
     * Gets a copy of a territory.
     * 
     * @param t the territory to copy
     * @return a copy of the territory given
     */
    Territory getCopyOfTerritory(Territory t);
}
