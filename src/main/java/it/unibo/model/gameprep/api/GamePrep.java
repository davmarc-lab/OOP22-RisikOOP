package it.unibo.model.gameprep.api;

import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Represents the game preparation phase.
 */
public interface GamePrep {
    /**
     * Prepares each players assiging them a deck of territories, an objective, and
     * troops.
     */
    void preparePlayers(final List<Player> players, final Deck<Territory> territoryDeck,
            final Pair<Deck<Objective>, Objective> objectives);

}
