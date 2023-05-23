package it.unibo.model.player.api;

import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

/**
 * This interface instace a player of the game.
 */
public interface Player {

    enum Color {

        BLACK("BLACK", 0, 0, 0),
        BLUE("BLUE", 0, 0, 255),
        RED("RED", 255, 0, 0),
        GREEN("GREEN", 0, 255, 0),
        YELLOW("YELLOW", 255, 255, 0),
        WHITE("WHITE", 255, 255, 255);

        private final String name;
        private final int r;
        private final int g;
        private final int b;

        Color(final String name, final int r, final int g, final int b) {
            this.name = name;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public String getName() {
            return this.name;
        }

        public int getRedValue() {
            return this.r;
        }

        public int getGreenValue() {
            return this.g;
        }

        public int getBlueValue() {
            return this.b;
        }
    }

    /**
     * This method returns player's id.
     * 
     * @return player's id
     */
    int getId();

    /**
     * This method return the number of armies in a {@code Territory}.
     * 
     * @param t territory
     * @return the number of armies in the territory
     */
    int getArmy(Territory t);

    /**
     * This method adds a {@code Territory} to player's territory.
     * 
     * @param territory stream of territories that will be added to player
     */
    void addTerritory(Territory territory);

    /**
     * This method removes a {@code Territory} from player's territory.
     * 
     * @param territory stream of territories that will be removed from player
     */
    void removeTerritory(Territory territory);

    /**
     * This method return the territories of player.
     * 
     * @return set of territories owned by player
     */
    Set<Territory> getTerritories();

    /**
     * This method gets player's color.
     * 
     * @return player color
     */
    Color getColorPlayer();

    /**
     * This method gets player's current objective.
     * 
     * @return player objective
     */
    Objective getObjective();

    /**
     * This method returns player's army deck.
     * 
     * @return player hand
     */
    Hand<Army> getPlayerHand();

    /**
     * This method returns player's territory deck.
     * 
     * @return player's territory deck
     */
    Deck<Territory> getTerritoryDeck();

    /**
     * This method sets player's id.
     * 
     * @param id player id
     */
    void setId(int id);

    /**
     * This method sets player's objective.
     * 
     * @param objective player objective
     */
    void setObjective(Objective objective);

    void addTroops(int numberTroops);
}
