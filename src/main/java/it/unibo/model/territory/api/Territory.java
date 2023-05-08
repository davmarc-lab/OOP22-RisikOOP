package it.unibo.model.territory.api;

import java.util.Set;

/**
 * 
 */
public interface Territory {

    /**
     * 
     * @return territory's name
     */
    String getName();

    /**
     * 
     * @return adjacent territories' set
     */
    Set<Territory> getAdjTerritories();

    /**
     * Add the given territory to the adjacents.
     * 
     * @param t
     *          the territory
     */
    void addAdjTerritory(Territory t);

    /**
     * 
     * @return the number of army in the territory
     */
    int getArmy();

    /**
     * Add the given number of armies to the territory.
     * 
     * @param n
     *          number of armies to add
     */
    void addArmy(int n);
}
