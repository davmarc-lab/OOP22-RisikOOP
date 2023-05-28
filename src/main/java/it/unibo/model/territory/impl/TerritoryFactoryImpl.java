package it.unibo.model.territory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.common.Pair;
import it.unibo.controller.reader.impl.JsonReaderTerritory;
/**
 * Implementation of the territory factory.
 */
public class TerritoryFactoryImpl implements TerritoryFactory {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameTerritory createTerritories() {
        Map<String, Set<Territory>> territories = new HashMap<>();
        Set<Pair<String, Set<Territory>>> set = new JsonReaderTerritory().readFromFile();
        set.forEach(p -> territories.put(p.getX(), p.getY()));
        return new GameTerritoryImpl(territories);
    }
}
