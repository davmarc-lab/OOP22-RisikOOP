package it.unibo.model.board.api;

import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;

/**
 * The {@code GameBoard} interface provides methods to interact with the game
 * board.
 */
public interface GameBoard {

    /**
     * Defines the result of a combat that was canceled.
     */
    Pair<Pair<Integer, Integer>, Boolean> CANCEL_COMBAT = new Pair<>(new Pair<>(0, 0), false);

    /**
     * Enumerating of bonus troops for each continent.
     */
    enum BonusTroops {
        /**
         * Bonus troops of the continent Oceania.
         */
        OCEANIA_TROOPS("Oceania", 2),

        /**
         * Bonus troops of the continent Europe.
         */
        EUROPE_TROOPS("Europe", 5),

        /**
         * Bonus troops of the continent South America.
         */
        SOUTH_AMERICA_TROOPS("South America", 2),

        /**
         * Bonus troops of the continent North America.
         */
        NORTH_AMERICA_TROOPS("North America", 5),

        /**
         * Bonus troops of the continent Africa.
         */
        AFRICA_TROOPS("Africa", 3),

        /**
         * Bonus troops of the continent Asia.
         */
        ASIA_TROOPS("Asia", 7);

        private final String continent;
        private final int bonusTroops;

        /**
         * Constructor to create the enum values.
         * 
         * @param continent the continetn's name
         * @param number    the number of bonus troops.
         */
        BonusTroops(final String continent, final int number) {
            this.continent = continent;
            this.bonusTroops = number;
        }

        /**
         * Retrieves the continent's name.
         * 
         * @return the continent's name.
         */
        public String getContinent() {
            return this.continent;
        }

        /**
         * Retrieves the bonus troops of a continent.
         * 
         * @return the continent's bonus troops
         */
        public int getBonusTroops() {
            return this.bonusTroops;
        }
    }

    /**
     * Initiate a combat between two territories.
     * 
     * @param attacker attacker territory
     * @param defender defender territory
     * @return a pair of results and the boolean result of the attack
     */
    Pair<Pair<Integer, Integer>, Boolean> instanceCombat(Pair<Player, Territory> attacker,
            Pair<Player, Territory> defender);

    /**
     * Initiate troops movement from a territory to another.
     * 
     * @param oldTerritory departure territory
     * @param newTerritory arrival territory
     */
    void instanceMovement(Territory oldTerritory, Territory newTerritory);

    /**
     * Retrieves the army deck.
     * 
     * @return the army deck's data structure
     */
    Deck<Army> getArmyDeck();

    /**
     * Retrieves the objective deck.
     * 
     * @return the objectives' data structure
     */
    Deck<Objective> getObjectives();

    /**
     * Retrieves the territory deck.
     * 
     * @return territory deck data structure
     */
    Deck<Territory> getTerritoryDeck();

    /**
     * Retrieves a list of all player in the game.
     * 
     * @return players that are playing
     */
    List<Player> getAllPlayers();

    /**
     * Retrieves the player that has a certain ID.
     * 
     * @param id the id
     * @return the player with the specified ID
     */
    Player getPlayerFromId(int id);

    /**
     * @return the GameTerritory
     */
    GameTerritory getGameTerritories();

    /**
     * Retrieves the {@code TurnManager} for managing the turns in the game.
     * 
     * @return turn manager
     */
    TurnManager getTurnManager();

    /**
     * Make a player draw a card.
     * 
     * @param player the player that draws a card
     */
    void playerDrawArmyCard(Player player);

    /**
     * Define the current player bonus troops depending on the territories he
     * controls.
     * 
     * @param player the player that will get the bonus troops
     */
    void defineBonusArmies(Player player);

    /**
     * Place troops on a territory.
     * 
     * @param territory the territory that will receive the troops
     * @param troops    the number of troops to be placed
     */
    void placeTroops(Territory territory, int troops);

}
