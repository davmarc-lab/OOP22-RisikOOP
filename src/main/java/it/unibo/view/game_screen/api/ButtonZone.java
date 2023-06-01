package it.unibo.view.game_screen.api;

import it.unibo.controller.api.MainController;

/**
 * The interface that defines the area inside the side zone where action
 * buttons are placed.
 */
public interface ButtonZone {

    /**
     * Sets the controller.
     * 
     * @param controller the main controller
     */
    void setController(MainController controller);
}
