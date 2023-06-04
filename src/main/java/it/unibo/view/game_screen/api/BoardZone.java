package it.unibo.view.game_screen.api;

import java.util.Set;

import it.unibo.controller.gamecontroller.api.MainController;

import java.awt.Dimension;

/**
 * This interface models a basic view containing the game map and the buttons.
 */
public interface BoardZone {

    /**
     * Disables the specified territories.
     * 
     * @param terr set of territories
     */
    void disableButtons(Set<String> terr);

    /**
     * Enables all buttons.
     */
    void enableAll();

    /**
     * Disables all buttons.
     */
    void disableAll();

    /**
     * @return MainController
     */
    MainController getController();

    /**
     * Sets the text color in the label with the number of troops in all territories.
     */
    void setTroopsView();

    /**
     * Update the label with the number of troops of the territory given.
     * 
     * @param territory the territory name
     */
    void updateTroopsView(String territory);

    Dimension getDimension();

}
