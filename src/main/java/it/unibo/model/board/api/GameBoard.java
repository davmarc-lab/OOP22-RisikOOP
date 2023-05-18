package it.unibo.model.board.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.game_loop.api.TurnManager;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Interface representing game board.
 */
public interface GameBoard {

    void instaceCombat(Territory tStriker, Territory tDefender);

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

    List<Player> getAllPlayers();

    /**
     * Gets the player who should be playing.
     * 
     * @return current player
     */
    Player getCurrentPlayer();

    /**
     * This method returns a shallow copy of the {@code TurnManager}.
     * 
     * @return object's shallow copy
     */
    TurnManager getTurnManager();

    /**
     * This method returns a shallow copy of the {@code GameBoard}.
     * 
     * @return object's shallow copy
     */
    GameBoard clone();
}
