package it.unibo.view.gamescreen.api;

import it.unibo.controller.playerhand.api.PlayerHandController;

/**
 * Provides methods to set the {@link PlayerHandController} to update the view.
 */
public interface CardZone {

    /**
     * Sets a new {@link PlayerHandController}.
     */
    void setController();

    /**
     * Updates the view with newer values.
     */
    void updateView();

    /**
     * Retrieves a copy of CardZone.
     * 
     * @return a copy of CardZone
     */
    CardZone getCopy();
}
