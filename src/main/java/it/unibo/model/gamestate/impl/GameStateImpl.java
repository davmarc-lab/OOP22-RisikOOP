package it.unibo.model.gamestate.impl;

import java.util.List;

import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.gamestate.api.GameState;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

public class GameStateImpl implements GameState {

    GameLoop gameLoop;

    @Override
    public boolean isGameFinished() {
        return checkIfPlayerWon();
    }

    private boolean checkIfPlayerWon() {
        List<Player> players = gameLoop.getBoard().getAllPlayers();
        for (Player player : players) {
            if (player.getObjective().getObjectiveType().equals(Objective.ObjectiveType.DESTROY)) {
                if (players.stream()
                        .filter(p -> p.getColorPlayer().getName()
                        .equals(player.getObjective().getCheckObjectives().getY().get(0)))
                        .findAny().get().getTerritories().isEmpty()) {
                    player.getObjective().setComplete();
                    return true;
                }
            } else {
                if (player.getObjective().getCheckObjectives().getY().size() == 2) {
                    if (player.getTerritories().size() >= Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(0))) {
                        for (Territory t : player.getTerritories()) {
                            if (t.getTroops() >= Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(1))) {
                                player.getObjective().setComplete();
                                return true;
                            }
                        }
                    }
                } else {
                    if (Boolean.valueOf(player.getObjective().getCheckObjectives().getY().get(2))) {
                        if ((player.getTerritories().containsAll(gameLoop.getBoard().getGameTerritories().getTerritoryMap().get(player.getObjective().getCheckObjectives().getY().get(0)))) &&
                            (player.getTerritories().containsAll(gameLoop.getBoard().getGameTerritories().getTerritoryMap().get(player.getObjective().getCheckObjectives().getY().get(1))))) {
                            for (String continent : gameLoop.getBoard().getGameTerritories().getTerritoryMap().keySet()) {
                                if (player.getTerritories().containsAll(gameLoop.getBoard().getGameTerritories().getTerritoryMap().get(continent)));
                                player.getObjective().setComplete();
                                return true;
                            }
                        }
                    } else {
                        if ((player.getTerritories().containsAll(gameLoop.getBoard().getGameTerritories().getTerritoryMap().get(player.getObjective().getCheckObjectives().getY().get(0)))) &&
                            (player.getTerritories().containsAll(gameLoop.getBoard().getGameTerritories().getTerritoryMap().get(player.getObjective().getCheckObjectives().getY().get(1))))) {
                            player.getObjective().setComplete();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
