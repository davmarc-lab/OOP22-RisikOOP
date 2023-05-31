package it.unibo.model.gameprep.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameprep.api.GamePrep;
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
        this.assignTerritories(this.territoryDeck);
        this.assignObjectives(this.objectives);
        this.assignTroops();
    }

    /**
     * Assigns territories to the players using the initial territory deck.
     *
     * @param territoryDeck the deck of territories to assign
     */
    private void assignTerritories(final Deck<Territory> territoryDeck) {
        this.players.forEach(player -> IntStream.range(0, Constants.MAX_CARDS_FOR_EACH_PLAYER / Constants.MAX_PLAYERS)
                .forEach(i -> player.addTerritory(territoryDeck.drawCard())));
    }

    /**
     * Assigns objectives to the players using the initial objective deck and
     * default objective.
     *
     * @param objectives a pair consisting of the objective deck and the default
     *                   objective
     */
    private void assignObjectives(final Pair<Deck<Objective>, Objective> objectives) {
        final List<String> colors = this.players.stream().map(p -> p.getColorPlayer().getName()).toList();
        final List<Objective> unaviableColors = new ArrayList<>();
        for (final Objective objective : objectives.getX().getDeck()) {
            if (objective.getObjectiveType().equals(Objective.ObjectiveType.DESTROY)
                    && !colors.contains(objective.getDescription())) {
                unaviableColors.add(objective);
            }
        }
        objectives.getX().getDeck().removeAll(unaviableColors);
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
     * Assigns troops to the players using the initial army deck.
     */
    private void assignTroops() {
        this.players.stream().forEach(p -> p.addTroops(Constants.TROOPS));
        this.players.stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
    }
}
