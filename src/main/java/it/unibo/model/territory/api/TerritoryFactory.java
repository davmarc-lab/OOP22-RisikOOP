package it.unibo.model.territory.api;

/**
 * Represents a factory that creates territories from a JSON file.
 */
public interface TerritoryFactory {

    /**
     * Creates the map of all territories.
     * 
     * @return the map of all territories
     */
    GameTerritory createTerritories();
}
