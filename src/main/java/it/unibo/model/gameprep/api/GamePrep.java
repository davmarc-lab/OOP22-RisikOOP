package it.unibo.model.gameprep.api;

import it.unibo.common.Pair;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

public interface GamePrep {
    void assignTerritories(Deck<Territory> territoryDeck);

    void assignObjectives(Pair<Deck<Objective>, Objective> objectives);

    void assignTroops(Deck<Army> armyDeck);
}
