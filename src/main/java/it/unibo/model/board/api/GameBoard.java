package it.unibo.model.board.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;

/**
 * Interface representing game board.
 */
public interface GameBoard {

    enum BonusTroops {
        OCEANIA_TROOPS("Oceania", 2),
        EUROPE_TROOPS("Europe", 5),
        SOUTH_AMERICA_TROOPS("South America", 2),
        NORTH_AMERICA_TROOPS("North America", 5),
        AFRICA_TROOPS("Africa", 3),
        ASIA_TROOPS("Asia", 7);

        private final String continent;
        private final int bonusTroops;

        BonusTroops(final String continent, final int number) {
            this.continent = continent;
            this.bonusTroops = number;
        }

        public String getContinent() {
            return this.continent;
        }

        public int getBonusTroops() {
            return this.bonusTroops;
        }
    }

    /**
     * Maximum number of player in the game.
     */
    static final int MAX_PLAYER = 3;

    /**
     * This method starts a combat between two players.
     * 
     * @param tStriker  striker territory
     * @param tDefender defender territory
     */
    void instaceCombat(Pair<Player, Territory> striker, Pair<Player, Territory> defender);

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

    GameTerritory getGameTerritories();

    /**
     * This method returns a shallow copy of the {@code TurnManager}.
     * 
     * @return object's shallow copy
     */
    TurnManager getTurnManager();

    /**
     * This method is called when player gets the armies to be placed in his
     * territories after changing turn.
     */
    void defineBonusArmies();

    /**
     * This method is called when players need to move troops from their
     * bonus troops to some territories.
     */
    void placeTroops();
}
