package it.unibo.model.gameloop.api;

import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.territory.api.Territory;

/**
 * Processes the input received from the view
 * and tell the view what to render.
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
     * @param territories the territories to be displayed
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
     * Retrieves the {@link TurnManager} of the game.
     * 
     * @return the turn manager
     */
    TurnManager getTurnManager();

    /**
     * Randomizes the distribution of troops on territories.
     */
    void randomize();

    /**
     * Retrieves a copy of the current {@link GameLoop}.
     * 
     * @return a copy of the current game loop
     */
    GameLoop getCopy();

    /**
     * Sets the controller.
     * 
     * @param controller the controller
     */
    void setController(MainController controller);

}
