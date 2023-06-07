package it.unibo.controller.gamecontroller.api;

import java.util.Set;

import it.unibo.gameengine.api.GameEngine;
import it.unibo.model.player.api.Player;
import it.unibo.view.gamescreen.GamePanel;

/**
 * It models the main controller that allows the {@link GameZone} to
 * communicate with the model.
 */
public interface MainController {

    /**
     * Sends an input received from the board to the model.
     * 
     * @param input the name of the territory that was clicked
     */
    void sendInput(String input);

    /**
     * Sends a message to be displayed on the GUI.
     * 
     * @param message the message to be displayed
     */
    void sendMessage(String message);

    /**
     * Disables the specified territories in the GUI.
     * 
     * @param territories the territories to be disabled
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
     * Retrieves the current {@link GameEngine}.
     * 
     * @return the game engine
     */
    GameEngine getGameEngine();

    /**
     * Retrieves the {@link GameZone} view.
     * 
     * @return the area that contains the board and the sidebar
     */
    GamePanel getGamePanel();

    /**
     * Starts game engine.
     */
    void startEngine();

    /**
     * Randomizes the distribution of troops on territories.
     */
    void randomize();

    /**
     * Retrieves the {@link Player} giving a {@code Territory} name.
     * 
     * @param territory the name of the territory
     * @return the player that possesses that territory
     */
    Player getPlayerFromTerritory(String territory);

    /**
     * Retrieves the current player.
     * 
     * @return the player that is currently playing
     */
    Player getCurrentPlayer();

    /**
     * Adds troops to the current player's bonus troops.
     * 
     * @param troops number of troops
     */
    void addBonustTroops(int troops);

    /**
     * Restarts application.
     */
    void restartApp();

    /**
     * Updates the number of armies in the territory provided.
     * 
     * @param name the name of the territory
     */
    void updateSquare(String name);

    /**
     * Sets the text color in the label with the number of troops in all
     * territories.
     */
    void setSquares();

    /**
     * Updates the Info at the top of sidebar.
     */
    void updateInfo();

    /**
     * Updates the cards of the current player at the centrer of sidebar.
     */
    void updateCards();

    /**
     * Sets the controller to interact with the player {@code Hand}.
     */
    void setCardController();

    /**
     * Retrieves a copy of MainController.
     * 
     * @return a copy of MainController
     */
    MainController getCopy();
}
