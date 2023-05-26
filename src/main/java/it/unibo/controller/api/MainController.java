package it.unibo.controller.api;

import java.util.Set;

import it.unibo.model.territory.api.Territory;

/**
 * This interface models a controller that allows the board view to communicate
 * with the model.
 */
public interface MainController {

    /**
     * Sends the input received from the board to the model.
     * 
     * @param input what the player clicked
     */
    void sendInput(Object input);

    /**
     * Sends a message to be displayed on the GUI.
     * 
     * @param message the message to be attached to the notification
     */
    void sendMessage(String message);

    /**
     * Disables the specified territories in the GUI.
     * @param territories the Set of territories
     */
    void disableTerritories(Set<Territory> territories);

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
}
