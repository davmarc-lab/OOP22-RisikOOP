package it.unibo.model.gameprep.api;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

public interface GamePrep {

    void createObjectiveDeck();

    Deck<Objective> getObjectiveDeck();

    void createTerritoryDeck();

    Deck<Territory> getTerritoryDeck();

    void createArmyDeck();

    Deck<Army> getArmyDeck();

}
