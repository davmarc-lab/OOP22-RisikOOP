package it.unibo.model.gameprep.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.common.Pair;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.modelconstants.ModelConstants;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of the GamePrep interface.
 */
public class GamePrepImpl implements GamePrep {

    private final List<Player> players;
    private final Deck<Territory> territoryDeck;
    private final Pair<Deck<Objective>, Objective> objectives;

    /**
     * Constructs a GamePrepImpl object with the specified players, territories,
     * objectives and armies.
     * 
     * @param players       the list of players
     * @param territoryDeck the initial deck of territories
     * @param objectives    the initial deck of objectives
     */
    public GamePrepImpl(final List<Player> players, final Deck<Territory> territoryDeck,
            final Pair<Deck<Objective>, Objective> objectives) {
        this.players = players;
        this.territoryDeck = territoryDeck;
        this.objectives = objectives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preparePlayers() {
        this.assignTerritories();
        this.assignObjectives();
        this.assignTroops();
    }

    /**
     * Assigns territories to the players.
     */
    private void assignTerritories() {
        this.players.forEach(player -> IntStream.range(0, ModelConstants.MAX_CARDS_FOR_EACH_PLAYER / ModelConstants.MAX_PLAYERS)
                .forEach(i -> player.addTerritory(territoryDeck.drawCard())));
    }

    /**
     * Assigns objectives to the players.
     */
    private void assignObjectives() {
        final List<String> colors = this.players.stream().map(p -> p.getColorPlayer().getName()).toList();
        final List<Objective> unaviableColors = new ArrayList<>();
        for (final Objective objective : objectives.getX().getDeck()) {
            if (objective.getObjectiveType().equals(Objective.ObjectiveType.DESTROY)
                    && !colors.contains(objective.getDescription())) {
                unaviableColors.add(objective);
            }
        }
        unaviableColors.forEach(c -> objectives.getX().removeCard(c));
        for (final Player player : this.players) {
            final Objective drawnObj = objectives.getX().drawCard();
            if (drawnObj.getDescription().equals(player.getColorPlayer().getName())) {
                player.setObjective(objectives.getY());
            } else {
                player.setObjective(drawnObj);
            }
        }
    }

    /**
     * Assigns troops to the players.
     */
    private void assignTroops() {
        this.players.stream().forEach(p -> p.addTroops(ModelConstants.TROOPS));
        this.players.stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
    }
}
