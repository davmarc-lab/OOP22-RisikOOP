package it.unibo.model.player.api;

import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

/**
 * This interface instace a player of the game.
 */
public interface Player {

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
     * @return player's color
     */
    ColorPlayer getColorPlayer();

    /**
     * This method gets player's current hand of cards.
     * 
     * @return player's deck
     */
    Deck<Army> getHandCards();

    /**
     * This method gets player's current objective.
     * 
     * @return player's objective
     */
    Objective getObjective();

    Deck<Territory> getPlayerHand();

    Player getCopy();

    void setId(int id);
}
