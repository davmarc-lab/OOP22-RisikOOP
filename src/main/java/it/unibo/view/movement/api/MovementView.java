package it.unibo.view.movement.api;

import it.unibo.controller.movement.api.MovementController;

/**
 * This interface define the methods for a {@code MovementView}.
 */
public interface MovementView {

    /**
     * Start the movement view.
     */
    void startPopup();

    /**
     * Sets the movement controller.
     * 
     * @param controller movement controller
     */
    void setController(MovementController controller);
}
