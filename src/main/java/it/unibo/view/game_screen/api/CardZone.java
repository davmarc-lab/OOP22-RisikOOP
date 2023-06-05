package it.unibo.view.game_screen.api;

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
     * @return a copy of CardZone
     */
    CardZone getCopy();
}
