package it.unibo.gameengine.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.common.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.gameengine.api.GameState;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;

/**
 * Implementation of the {@link GameState} interface.
 * Represents the current state of a game.
 */
public class GameStateImpl implements GameState {

    private MainController mc;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return checkIfPlayerWon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getWinner() {
        return this.mc.getGameEngine().getBoard().getAllPlayers().stream()
                .filter(p -> p.getObjective().isComplete())
                .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final MainController controller) {
        this.mc = controller.getCopy();
    }

    /**
     * Checks if any player has won the game.
     *
     * @return {@code true} if a player has won the game, {@code false} otherwise
     */
    private boolean checkIfPlayerWon() {
        final List<Player> players = this.mc.getGameEngine().getBoard().getAllPlayers();
        final List<Pair<Integer, Boolean>> results = new ArrayList<>();
        for (final Player player : players) {
            final String armyColor = player.getObjective().getCheckObjectives().getY().get(0);
            if (player.getObjective().getObjectiveType().equals(Objective.ObjectiveType.DESTROY)) {
                results.add(new Pair<>(player.getId(), isColorDestroyed(player, armyColor, players)));
            } else if (player.getObjective().getCheckObjectives().getY().size() == 2) {
                results.add(new Pair<>(player.getId(), checkNumberOfConqueredTerritories(player)));
            } else {
                results.add(new Pair<>(player.getId(), checkConqueredContinent(player)));
            }
        }
        return results.stream().anyMatch(p -> p.getY().equals(true));
    }

    /**
     * Checks if a player has destroyed the specified armyColor.
     *
     * @param player    the player to check
     * @param armyColor the armyColor to be destroyed
     * @param players   the list of all players
     * @return {@code true} if the player has destroyed the army color,
     *         {@code false} otherwise
     */
    private boolean isColorDestroyed(final Player player, final String armyColor, final List<Player> players) {
        if (players.stream()
                .filter(p -> p.getColorPlayer().getName().equals(armyColor))
                .findAny().get().getTerritories().isEmpty()) {
            player.setObjectiveComplete();
            return true;
        }
        return false;
    }

    /**
     * Checks if a player has conquered the required number of territories with the
     * minimum number of troops.
     *
     * @param player the player to check
     * @return {@code true} if the player has completed the objective, {@code false}
     *         otherwise
     */
    private boolean checkNumberOfConqueredTerritories(final Player player) {
        final int numTerritoriesToConquer = Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(0));
        final int minNumTroops = Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(1));
        final boolean isObjectiveComplete = player.getTerritories().stream()
                .filter(t -> t.getTroops() >= minNumTroops)
                .limit(numTerritoriesToConquer)
                .count() >= numTerritoriesToConquer;
        if (isObjectiveComplete) {
            player.setObjectiveComplete();
            return true;
        }
        return false;
    }

    /**
     * Checks if a player has conquered the required continents.
     *
     * @param player the player to check
     * @return {@code true} if the player has completed the objective, {@code false}
     *         otherwise
     */
    private boolean checkConqueredContinent(final Player player) {
        final String firstContinent = player.getObjective().getCheckObjectives().getY().get(0);
        final String secondContinent = player.getObjective().getCheckObjectives().getY().get(1);
        final boolean thirdContinent = Boolean.valueOf(player.getObjective().getCheckObjectives().getY().get(2));

        if (thirdContinent && isContinentConquered(player, firstContinent)
                && isContinentConquered(player, secondContinent)) {
            if (this.mc.getGameEngine().getBoard().getGameTerritories().getTerritoryMap().keySet().stream()
                    .filter(c -> !c.equals(firstContinent) && !c.equals(secondContinent))
                    .anyMatch(continent -> isContinentConquered(player, continent))) {
                player.setObjectiveComplete();
                return true;
            }
        } else if (isContinentConquered(player, firstContinent) && isContinentConquered(player, secondContinent)) {
            player.setObjectiveComplete();
            return true;
        }

        return false;
    }

    /**
     * Checks if a player has conquered the specified continent.
     *
     * @param player    the player to check
     * @param continent the name of the continent to be conquered
     * @return {@code true} if the player has conquered the continent, {@code false}
     *         otherwise
     */
    private boolean isContinentConquered(final Player player, final String continent) {
        return player.getTerritories().containsAll(this.mc.getGameEngine().getBoard().getGameTerritories()
                .getTerritoryMap().get(continent));
    }
}
