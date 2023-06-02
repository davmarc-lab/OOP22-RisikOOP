package it.unibo.model.gamestate.impl;

import java.util.List;

import it.unibo.controller.api.MainController;
import it.unibo.model.gamestate.api.GameState;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;

public class GameStateImpl implements GameState {

    private final MainController mc;

    public GameStateImpl(final MainController mc) {
        this.mc = mc;
    }

    @Override
    public boolean isGameFinished() {
        return checkIfPlayerWon();
    }

    @Override
    public Player getWinner() {
        return this.mc.getGameLoop().getBoard().getAllPlayers().stream()
                .filter(p -> p.getObjective().isComplete())
                .findAny().get();
    }

    private boolean checkIfPlayerWon() {
        final List<Player> players = this.mc.getGameLoop().getBoard().getAllPlayers();
        for (final Player player : players) {
            final String armyColor = player.getObjective().getCheckObjectives().getY().get(0);
            if (player.getObjective().getObjectiveType().equals(Objective.ObjectiveType.DESTROY)) {
                return isColorDestroyed(player, armyColor, players);
            } else if (player.getObjective().getCheckObjectives().getY().size() == 2) {
                return checkNumberOfConqueredTerritories(player);
            } else {
                return checkConqueredContinent(player);
            }
        }
        return false;
    }

    private boolean isColorDestroyed(final Player player, final String armyColor, final List<Player> players) {
        if (players.stream()
                        .filter(p -> p.getColorPlayer().getName()
                        .equals(armyColor))
                        .findAny().get().getTerritories().isEmpty()) {
            player.getObjective().setComplete();
            return true;
        }
        return false;
    }
    
    private boolean checkNumberOfConqueredTerritories(final Player player) {
        final int numTerritoriesToConquer = Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(0));
        final int minNumArmies = Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(1));
        final boolean isObjectiveComplete = player.getTerritories().stream()
                .filter(t -> t.getTroops() >= minNumArmies)
                .limit(numTerritoriesToConquer)
                .count() == numTerritoriesToConquer;
        if (isObjectiveComplete) {
            player.getObjective().setComplete();
            return true;
        }
        return false;
    }
    
    private boolean checkConqueredContinent(final Player player) {
        final String firstContinent = player.getObjective().getCheckObjectives().getY().get(0);
        final String secondContinent = player.getObjective().getCheckObjectives().getY().get(1);
        final boolean thirdContinent = Boolean.valueOf(player.getObjective().getCheckObjectives().getY().get(2));
        
        if (thirdContinent && isContinentConquered(player, firstContinent) && isContinentConquered(player, secondContinent)) {
            if (this.mc.getGameLoop().getBoard().getGameTerritories().getTerritoryMap().keySet().stream()
                    .filter(c -> !c.equals(firstContinent) && !c.equals(secondContinent))
                    .anyMatch(continent -> isContinentConquered(player, continent))) {
                player.getObjective().setComplete();
                return true;
            }
        } else if (isContinentConquered(player, firstContinent) && isContinentConquered(player, secondContinent)) {
            player.getObjective().setComplete();
            return true;
        }
        
        return false;
    }

    private boolean isContinentConquered(final Player player, final String continent) {
        return player.getTerritories().containsAll(this.mc.getGameLoop().getBoard().getGameTerritories().getTerritoryMap().get(continent));
    }
}
