package it.unibo.model.gameprep.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

public class GamePrepImpl implements GamePrep {

    private List<Player> players;

    public GamePrepImpl(final List<Player> players, final Deck<Territory> territoryDeck,
            final Pair<Deck<Objective>, Objective> objectives, final Deck<Army> armyDeck) {
        this.players = players;
        assignObjectives(objectives);
        assignTerritories(territoryDeck);
        assignTroops(armyDeck);
    }

    @Override
    public void assignTerritories(Deck<Territory> territoryDeck) {
        this.players.forEach(player -> IntStream.range(0, Constants.MAX_CARDS_FOR_EACH_PLAYER / Constants.MAX_PLAYERS)
                .forEach(i -> player.addTerritory(territoryDeck.drawCard())));
    }

    @Override
    public void assignObjectives(final Pair<Deck<Objective>, Objective> objectives) {
        List<String> colors = this.players.stream().map(p -> p.getColorPlayer().getName()).toList();
        var unaviableColors = new ArrayList<>();
        for (var objective : objectives.getX().getDeck()) {
            if (objective.getObjectiveType().equals(Objective.ObjectiveType.DESTROY)
                    && !colors.contains(objective.getDescription())) {
                unaviableColors.add(objective);
            }
        }
        objectives.getX().getDeck().removeAll(unaviableColors);
        for (final Player player : this.players) {
            Objective drawnObj = objectives.getX().drawCard();
            if (drawnObj.getDescription().equals(player.getColorPlayer().getName())) {
                player.setObjective(objectives.getY());
            } else {
                player.setObjective(drawnObj);
            }
        }
    }

    @Override
    public void assignTroops(Deck<Army> armyDeck) {
        this.players.stream().forEach(p -> p.addTroops(Constants.TROOPS));
        this.players.stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
    }
}
