package it.unibo.controller.combat.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.combat.api.CombatView;

/**
 * The interface for the {@code CombatController}.
 * It provides methods to manage combat between players and territories.
 */
public interface CombatController {

    /**
     * Starts the popup for combat.
     */
    void startPopup();

    /**
     * Gets the combat view frame.
     *
     * @return the combat view frame
     */
    CombatView getFrame();

    /**
     * Adds a number of troops to use in combat.
     *
     * @param n the value to be added
     */
    void addTroops(int n);

    /**
     * Checks if a given number is valid.
     *
     * @param value the number to be validated
     * @return {@code true} if the number is valid, false otherwise
     */
    boolean isNumberValid(int value);

    /**
     * Sets the combat value.
     */
    void setCombatOutcome();

    /**
     * Gets the final result of the combat.
     *
     * @return the final result
     */
    int getCombatOutcome();

    /**
     * Gets the player involved in the combat.
     *
     * @return the player
     */
    Player getCombatPlayer();

    /**
     * Gets the territory involved in the combat.
     *
     * @return the territory
     */
    Territory getCombatTerritory();

    /**
     * Cancels combat.
     */
    void cancelAction();

    /**
     * Checks if the attack has finished or is still running.
     * 
     * @return {@code true} if the attack hasn't finished, false otherwise
     */
    boolean isActionRunnig();
}
