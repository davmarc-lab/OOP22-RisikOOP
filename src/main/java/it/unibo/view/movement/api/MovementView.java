package it.unibo.view.movement.api;

import it.unibo.controller.movement.api.MovementController;

/**
 * Represents a graphical frame displaying the
 * movement view.
 */
public interface MovementView {

    /**
     * Start the movement view.
     * 
     * @param mc the controller linked to the popup
     */
    void startPopup(MovementController mc);
}
