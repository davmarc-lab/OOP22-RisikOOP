package it.unibo.model.territory.api;

import java.util.Set;

/**
 * Interface of territory.
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
     * @param t
     *          the territory
     */
    void addAdjTerritory(Territory t);

    /**
     * Gets the number of armies in the territory.
     * 
     * @return the number of armies in the territory
     */
    int getArmy();

    /**
     * Add the given number of armies.
     * 
     * @param n 
     *          number of armies to add
     */
    void addArmy(int n);
}
