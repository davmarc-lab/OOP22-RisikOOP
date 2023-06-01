package it.unibo.controller.api;

import java.util.Set;

import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.player.api.Player;
import it.unibo.view.game_screen.api.GameZone;

/**
 * This interface models a controller that allows the board view to communicate
 * with the model.
 */
public interface MainController {

    /**
     * Sends the input received from the board to the model.
     * 
     * @param input
     */
    void sendInput(Object input);

    /**
     * Sends a message to be displayed on the GUI.
     * 
     * @param message
     */
    void sendMessage(String message);

    /**
     * Disables the specified territories in the GUI.
     * 
     * @param territories
     */
    void disableTerritories(Set<String> territories);

    /**
     * Enables all the territories in the GUI.
     */
    void enableAllTerritories();

    /**
     * Disables all the territories in the GUI.
     */
    void disableAllTerritories();

    /**
     * Switches to combat phase.
     */
    void switchToCombat();

    /**
     * Switches to movement phase.
     */
    void switchToMovement();

    /**
     * Ends the players' turn.
     */
    void endTurn();

    /**
     * @return the game loop
     */
    GameLoop getGameLoop();

    /**
     * @return the area that contains the board and the side bar
     */
    GameZone getGameZone();

    /**
     * Starts game loop.
     */
    void startLoop();

    /**
     * Sets the game loop.
     * 
     * @param loop
     */
    void setGameLoop(GameLoop loop);

    /**
     * Sets the game zone.
     * 
     * @param gui
     */
    void setGameZone(GameZone gui);

    /**
     * Randomizes the distribution of troops on territories.
     */
    void randomize();

    /**
     * @param territory
     * @return the player that possesses that territory
     */
    Player getPlayerFromTerritory(String territory);

}
