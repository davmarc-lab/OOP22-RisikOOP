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
 * Implementation of {@link GamePrep} interface.
 * Provides a method to prepare the player for the game.
 */
public class GamePrepImpl implements GamePrep {

    /**
     * {@inheritDoc}
     */
    @Override
    public void preparePlayers(final List<Player> players, final Deck<Territory> territoryDeck,
            final Pair<Deck<Objective>, Objective> objectives) {
        this.assignTerritories(players, territoryDeck);
        this.assignObjectives(players, objectives);
        this.assignTroops(players);
    }

    /**
     * Assigns territories to the players.
     * 
     * @param players       players in the game
     * @param territoryDeck territory deck
     */
    private void assignTerritories(final List<Player> players, final Deck<Territory> territoryDeck) {
        players.forEach(
                player -> IntStream.range(0, ModelConstants.MAX_CARDS_FOR_EACH_PLAYER / ModelConstants.MAX_PLAYERS)
                        .forEach(i -> player.addTerritory(territoryDeck.drawCard())));
    }

    /**
     * Assigns objectives to the players.
     * 
     * @param players    players in the game
     * @param objectives objectives of the game
     */
    private void assignObjectives(final List<Player> players, final Pair<Deck<Objective>, Objective> objectives) {
        final List<String> colors = players.stream().map(p -> p.getColorPlayer().getName()).toList();
        final List<Objective> aviableColors = new ArrayList<>();

        colors.stream().forEach(color -> objectives.getX().getDeck().stream().filter(o -> o.getObjectiveType()
                .equals(Objective.ObjectiveType.DESTROY) && o.getDescription().contains(color))
                .forEach(obj -> aviableColors.add(obj)));

        objectives.getX().getDeck().stream().filter(o -> o.getObjectiveType().equals(Objective.ObjectiveType.DESTROY))
                .filter(o -> !aviableColors.contains(o)).forEach(o -> objectives.getX().removeCard(o));

        players.stream().forEach(player -> {
            final Objective drawnObj = objectives.getX().drawCard();
            final Objective finalObs = drawnObj.getDescription().contains(player.getColorPlayer().getName())
                    ? objectives.getY()
                    : drawnObj;
            player.setObjective(finalObs);
        });
    }

    /**
     * Assigns troops to the players.
     * 
     * @param players players in the game
     */
    private void assignTroops(final List<Player> players) {
        players.stream().forEach(p -> p.addTroops(ModelConstants.TROOPS));
        players.stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
    }
}
