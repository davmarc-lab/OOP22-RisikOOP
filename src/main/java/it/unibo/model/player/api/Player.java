package it.unibo.model.player.api;

import java.util.List;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

/**
 * The {@code Player} interface provide method to interact with the player.
 */
public interface Player {

    /**
     * Enumerating of player color.
     */
    enum Color {

        /**
         * Purple color.
         */
        PURPLE("PURPLE", 166, 77, 255),
        /**
         * Blue color.
         */
        BLUE("BLUE", 0, 0, 255),
        /**
         * Red color.
         */
        RED("RED", 255, 0, 0),
        /**
         * Green color.
         */
        GREEN("GREEN", 0, 255, 0),
        /**
         * Orange color.
         */
        ORANGE("ORANGE", 255, 133, 51),
        /**
         * White color.
         */
        WHITE("WHITE", 255, 255, 255);

        private final String name;
        private final int r;
        private final int g;
        private final int b;

        /**
         * Contsrutct a RGB color.
         * 
         * @param name color name
         * @param r    red value
         * @param g    green value
         * @param b    blue value
         */
        Color(final String name, final int r, final int g, final int b) {
            this.name = name;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        /**
         * Retrieves the color name.
         * 
         * @return color name
         */
        public String getName() {
            return this.name;
        }

        /**
         * Retrieves the color red value.
         * 
         * @return red value
         */
        public int getRedValue() {
            return this.r;
        }

        /**
         * Retrieves the color green value.
         * 
         * @return green value
         */
        public int getGreenValue() {
            return this.g;
        }

        /**
         * Retrieves the color blue value.
         * 
         * @return blue value
         */
        public int getBlueValue() {
            return this.b;
        }
    }

    /**
     * Retrieves the player's id.
     * 
     * @return player's id
     */
    int getId();

    /**
     * Sets player's id.
     * 
     * @param id player id
     */
    void setId(int id);

    /**
     * Retrieves the number of armies in a {@code Territory}.
     * 
     * @param t territory
     * @return the number of armies in the territory
     */
    int getArmy(Territory t);

    /**
     * Adds a {@code Territory} to player's territory.
     * 
     * @param territory stream of territories that will be added to player
     */
    void addTerritory(Territory territory);

    /**
     * Removes a {@code Territory} from player's territory.
     * 
     * @param territory stream of territories that will be removed from player
     */
    void removeTerritory(Territory territory);

    /**
     * Retrieves the territories of player.
     * 
     * @return set of territories owned by player
     */
    Set<Territory> getTerritories();

    /**
     * Retrieves player's color.
     * 
     * @return player color
     */
    Color getColorPlayer();

    /**
     * Retrieves player's current objective.
     * 
     * @return player objective
     */
    Objective getObjective();

    /**
     * This method sets player's objective.
     * 
     * @param objective player objective
     */
    void setObjective(Objective objective);

    /**
     * Retrieves player's {@code Hand}.
     * 
     * @return player hand
     */
    Hand getPlayerHand();

    /**
     * Adds an armyCard to player's hand.
     * 
     * @param card card that will be added to player's hand
     */
    void addCardToPlayerHand(Army card);

    /**
     * Removes a list of armyCards from the player's hand.
     * 
     * @param cards list of cards that will be removed from the player's hand
     */
    void removeCardsToPlayerHand(List<Army> cards);

    /**
     * Retrieves player's territory deck.
     * 
     * @return player's territory deck
     */
    Deck<Territory> getTerritoryDeck();

    /**
     * Adds bonustroops to the player.
     * 
     * @param numberTroops bonus troops
     */
    void addTroops(int numberTroops);

    /**
     * Retrieves the number of bonus troops of the player.
     * 
     * @return player bonus troops
     */
    int getTroops();
}
