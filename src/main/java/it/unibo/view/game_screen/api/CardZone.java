package it.unibo.view.game_screen.api;

import it.unibo.controller.playerhand.api.PlayerHandController;

/**
 * The {@link CardZone} interface provide methods to set the
 * {@link PlayerHandController} to update the view.
 */
public interface CardZone {

    /**
     * Sets the {@link PlayerHandController}.
     * 
     * @param controller
     */
    void setController(PlayerHandController controller);

    /**
     * Updates the view with newer values.
     */
    void updateView();

    CardZone getCopy();

}
