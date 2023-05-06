package it.unibo.model.player.api;

import java.util.Set;
import java.util.stream.Stream;

import it.unibo.model.territory.api.Territory;

public interface Player {
    int getId();

    int getArmy(final Territory t);

    void addTerritory(final Stream<Territory> territory);

    void removeTerritory(final Stream<Territory> territory);

    Set<Territory> getTerritories();
}