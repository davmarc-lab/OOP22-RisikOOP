package it.unibo.model.board.api;

import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

/**
 * Interface representing game board.
 */
public interface GameBoard {

    /**
     * Get the data structure that manage all territories.
     * 
     * @return the territories' data structure
     */
    Map<String, Set<Territory>> getTerritories();

    /**
     * Get the data structure that manage army deck.
     * 
     * @return the army deck's data structure
     */
    Deck<Army> getArmyDeck();

    /**
     * Get the data structure that manage objective deck.
     * 
     * @return the objectives' data structure
     */
    Deck<Objective> getObjectives();

    /**
     * Get the data structure that manage territory deck.
     * 
     * @return territory deck data structure
     */
    Deck<Territory> getTerritoryDeck();

}
