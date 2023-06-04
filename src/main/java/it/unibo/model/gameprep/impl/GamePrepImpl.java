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
        this.players.forEach(
                player -> IntStream.range(0, ModelConstants.MAX_CARDS_FOR_EACH_PLAYER / ModelConstants.MAX_PLAYERS)
                        .forEach(i -> player.addTerritory(territoryDeck.drawCard())));
    }

    /**
     * Assigns objectives to the players.
     */
    private void assignObjectives() {
        final List<String> colors = this.players.stream().map(p -> p.getColorPlayer().getName()).toList();
        final List<Objective> aviableColors = new ArrayList<>();

        colors.stream().forEach(color -> objectives.getX().getDeck().stream().filter(o -> o.getObjectiveType()
                .equals(Objective.ObjectiveType.DESTROY) && o.getDescription().contains(color))
                .forEach(obj -> aviableColors.add(obj)));

        objectives.getX().getDeck().stream().filter(o -> o.getObjectiveType().equals(Objective.ObjectiveType.DESTROY))
                .filter(o -> !aviableColors.contains(o)).forEach(o -> objectives.getX().removeCard(o));

        this.players.stream().forEach(player -> {
            final Objective drawnObj = objectives.getX().drawCard();
            final Objective finalObs = drawnObj.getDescription().contains(player.getColorPlayer().getName())
                    ? objectives.getY()
                    : drawnObj;
            player.setObjective(finalObs);
        });
    }

    /**
     * Assigns troops to the players.
     */
    private void assignTroops() {
        this.players.stream().forEach(p -> p.addTroops(ModelConstants.TROOPS));
        this.players.stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
    }
}
