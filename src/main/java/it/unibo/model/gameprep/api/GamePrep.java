package it.unibo.model.gameprep.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

public interface GamePrep {

    void createObjectiveDeck();

    void createTerritoryDeck();

    void createArmyDeck();

    void createPlayers();

    void assignTerritories();

    void assignObjectives();

    void assignTroops();

    Deck<Objective> getObjectiveDeck();

    Deck<Territory> getTerritoryDeck();

    Deck<Army> getArmyDeck();

    Map<String, Set<Territory>> getTerritoryMap();

    List<Player> getPlayers();
}
