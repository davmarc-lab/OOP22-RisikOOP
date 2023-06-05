package it.unibo.model.gameprep.api;

import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Provides a method to prepare the player for the game.
 */
public interface GamePrep {

    /**
     * Prepares each players assiging them a deck of territories, an objective, and
     * the number of troops.
     * 
     * @param players       game players
     * @param territoryDeck deck with all territories
     * @param objectives    {@code Pair} containing an objective deck and the
     *                      default objectibe
     */
    void preparePlayers(List<Player> players, Deck<Territory> territoryDeck,
            Pair<Deck<Objective>, Objective> objectives);
}
