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
     * Retrives the {@link GameBoard} of the game.
     * 
     * @return the game board
     */
    GameBoard getBoard();

    /**
     * Retrieves the {@link MainController} of the application.
     * 
     * @return the controller
     */
    MainController getController();

    /**
     * Retrieves the {@link TurnManager} of the game.
     * 
     * @return the turn manager
     */
    TurnManager getTurnManager();

    /**
     * Retrieves the list of territories that the user can interface.
     * 
     * @return list of selectable territories
     */
    List<Territory> getSelectedTerritories();

    /**
     * Retrieves the list of territories that the user cannot interface.
     * 
     * @return list of non selectable territories
     */
    List<Territory> getDisabledTerritories();

    /**
     * Retrieves the {@link GameState} of the game.
     * 
     * @return game state of the game
     */
    GameState getGameState();

    /**
     * Randomizes the distribution of troops on territories.
     */
    void randomize();

}
