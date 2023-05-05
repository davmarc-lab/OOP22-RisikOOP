package it.unibo.model.territory.api;

import java.util.Set;

public interface Territory {

    String getName();

    Set<Territory> getAdjTerritories();

    void addAdjTerritory(Territory t);
}
