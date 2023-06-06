package it.unibo.controller.movement.api;

import it.unibo.model.territory.api.Territory;

/**
 * Interface for the {@code MovementController}.
 * The implementation use as field a {@code Pair} containig the source and the
 * destination of the troops.
 */
public interface MovementController {

    /**
     * Opens the movement popup.
     */
    void startPopup();

    /**
     * Increments/decrements the current value of user input.
     * 
     * @param n user input
     */
    void addValue(int n);

    /**
     * Checks if the value is valid or not for the movement.
     * 
     * @param value input value
     * @return {@code true} if the value is valid, {@code false} otherwise
     */
    boolean isNumberValid(int value);

    /**
     * Confirms the final user values if {@code isNumberValid()} returns
     * {@code true}.
     */
    void setValue();

    /**
     * Retrieves the final value after the validity control pass.
     * 
     * @return correct input value
     */
    int getFinalResult();

    /**
     * Retrieves the source territory of the {@code Pair}.
     * 
     * @return the source territory
     */
    Territory getFirstTerritory();

    /**
     * Retrieves the destination territory of the {@code Pair}.
     * 
     * @return the destination territory
     */
    Territory getSecondTerritory();

    /**
     * Cancels movement.
     */
    void cancelAction();

    /**
     * Checks if the movement has finished.
     * 
     * @return {@code true} if the movement hasn't finished yet, {@code false} otherwise
     */
    boolean isActionRunning();
}
