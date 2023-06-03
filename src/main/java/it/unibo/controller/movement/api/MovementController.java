package it.unibo.controller.movement.api;

import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;

/**
 * Interface for the movement controller.
 * The implementation use as field a {@code Pair} containig the source and the
 * destination of the troops.
 */
public interface MovementController {

    /**
     * Opens the movement popup.
     */
    void startPopup();

    /**
     * This method returns the popup frame.
     * 
     * @return popup frame
     */
    MovementView getFrame();

    /**
     * This method set the popup frame.
     * 
     * @param frame
     */
    void setFrame(MovementView frame);

    /**
     * This method increments/decrements the current value of user input.
     * 
     * @param n user input
     */
    void addValue(int n);

    /**
     * This method checks if the value is valid or not for the movement.
     * 
     * @param value input value
     * @return {@code true} if the value is valid, {@code false} if the value is not
     *         valid
     */
    boolean isNumberValid(int value);

    /**
     * This method confirms the final user values if {@code isNumberValid()} returns
     * {@code true}.
     */
    void setValue();

    /**
     * This method returns the final value after the validity control pass.
     * 
     * @return correct input value
     */
    int getFinalResult();

    /**
     * This method returns the first field of the {@code Pair}.
     * 
     * @return the second field
     */
    Territory getFirstObject();

    /**
     * This method returns the second field of the {@code Pair}.
     * 
     * @return the second field
     */
    Territory getSecondObject();
    
    void cancelAction();

    boolean isActionRunnig();
}
