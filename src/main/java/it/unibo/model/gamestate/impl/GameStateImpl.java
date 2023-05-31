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
                        .filter(p -> p.getColorPlayer()
                        .equals(player.getObjective().getCheckObjectives().getY().get(0)))
                        .findAny().get().getTerritories().isEmpty()) {
                    return true;
                }
            } else {
                if (player.getTerritories().size() >= Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(0))) {
                    for (Territory t : gameLoop.getBoard().getGameTerritories().getTerritories()) {
                        if (t.getTroops() >= Integer.parseInt(player.getObjective().getCheckObjectives().getY().get(1))) {
                            return true;
                        }
                    }
                } else if (gameLoop.getBoard().getCurrentPlayer().getContinents().size() >= gameLoop.getBoard()
                        .getCurrentPlayer()
                        .getObjective().getCheckObjectives().getFirst().getNumContinentsToConquer()) {
                    return true;
                }

            }
        }
    }

}
