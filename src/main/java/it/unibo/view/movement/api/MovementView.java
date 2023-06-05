package it.unibo.view.movement.api;

import it.unibo.controller.movement.api.MovementController;

/**
 * This interface define the methods for a {@code MovementView}.
 */
public interface MovementView {

    /**
     * Start the movement view.
     * 
     * @param mc the controller linked to the popup
     */
    void startPopup(MovementController mc);

}
