package it.unibo.view.game_screen.api;

import java.util.Set;

import it.unibo.controller.api.MainController;
import it.unibo.model.territory.api.Territory;

/**
 * This interface models a basic view containing the game map and the buttons.
 */
public interface BoardZone {

    /**
     * Disables the specified territories.
     * 
     * @param terr set of territories
     */
    void disableButtons(Set<Territory> terr);

    /**
     * Enables all buttons.
     */
    void enableAll();

    /**
     * Disables all buttons.
     */
    void disableAll();

    /**
     * Sets the controller.
     * 
     * @param controller a MainController
     */
    void setController(MainController controller);

    /**
     * @return MainController
     */
    MainController getController();

}
