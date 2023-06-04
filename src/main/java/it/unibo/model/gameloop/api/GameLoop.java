package it.unibo.model.gameloop.api;

import java.util.List;
import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.gamestate.api.GameState;
import it.unibo.model.territory.api.Territory;

/**
 * This interface models a game loop, its job is to take input, process it and
 * then update the GUI.
 */
public interface GameLoop {

    /**
     * Starts game loop.
     */
    void start();

    /**
     * Processes the input.
     * 
     * @param input the name of the territory that was clicked
     */
    void processInput(String input);

    /**
     * Starts combat if the phase is PLAY_CARDS or COMBAT.
     */
    void startCombat();

    /**
     * Starts movement if the phase is PLAY_CARDS, COMBAT or MOVEMENT.
     */
    void startMovement();

    /**
     * Ends the player's turn (switches to END_TURN phase) and passes control to the
     * next player.
     */
    void endPlayerTurn();

    /**
     * Sets the territories to be displayed on the GUI.
     * 
     * @param territories
     */
    void setAvailableTerritories(Set<Territory> territories);

    /**
     * @return the phase manager
     */
    PhaseManager getPhaseManager();

    /**
     * @return the game board
     */
    GameBoard getBoard();

    /**
     * @return the controller
     */
    MainController getController();

    /**
     * @return the turn manager
     */
    TurnManager getTurnManager();

    List<Territory> getSelectedTerritories();

    Set<Territory> getDisabledTerritories();

    GameState getGameState();

    /**
     * Randomizes the distribution of troops on territories.
     */
    void randomize();

}
