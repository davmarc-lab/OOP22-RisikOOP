package it.unibo.model.player.api;

import java.util.Set;
import java.util.stream.Stream;

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
     * This method adds a {@code Stream<Territory>} to player's territory.
     * 
     * @param territory stream of territories that will be added to player
     */
    void addTerritory(Stream<Territory> territory);

    /**
     * This method removes a {@code Stream<Territory>} from player's territory.
     * 
     * @param territory stream of territories that will be removed from player
     */
    void removeTerritory(Stream<Territory> territory);

    /**
     * This method return the territories of player.
     * 
     * @return set of territories owned by player
     */
    Set<Territory> getTerritories();
}
