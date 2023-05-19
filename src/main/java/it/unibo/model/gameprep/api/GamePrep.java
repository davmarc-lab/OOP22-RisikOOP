package it.unibo.model.gameprep.api;

import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

public interface GamePrep {

    void createObjectiveDeck();
    
    void createTerritoryDeck();
    
    void createArmyDeck();
    
    Deck<Objective> getObjectiveDeck();
    
    Deck<Territory> getTerritoryDeck();

    Map<String, Set<Territory>> getTerritoryMap();

    Deck<Army> getArmyDeck();
}
