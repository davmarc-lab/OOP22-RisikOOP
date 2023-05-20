package it.unibo.model.board.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Interface representing game board.
 */
public interface GameBoard {

    /**
     * Maximum number of player in the game.
     */
    int MAX_PLAYER = 3;

    /**
     * This method starts a combat between two players.
     * 
     * @param tStriker striker territory
     * @param tDefender defender territory
     */
    void instaceCombat(Territory tStriker, Territory tDefender);

    /**
     * This method starts moving armies from a territory to another one.
     * 
     * @param oldTerritory departure territory
     * @param newTerritory arrival territory
     */
    void instanceMovement(Territory oldTerritory, Territory newTerritory);

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

    /**
     * This methods returns all players in the game.
     * 
     * @return players that are playing
     */
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
    GameBoard getCopy();
}
