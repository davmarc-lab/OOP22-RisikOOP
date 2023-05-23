package it.unibo.model.territory.api;

/**
 * Interface of the factory of territories.
 */
public interface TerritoryFactory {

    /**
     * Creates the map of all territories.
     * 
     * @return the map of all territories
     */
    GameTerritory createTerritories();
}
