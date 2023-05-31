package it.unibo.view.combat.api;

import it.unibo.controller.combat.api.CombatController;

/**
 * Represents the gui for the combat phase.
 */
public interface CombatView {

    /**
     * Starts the combat popup.
     * Displays a dialog box for the combat phase, allowing the player to confirm or cancel the action.
     */
    void startPopup();

    /**
     * Sets the controller for the combat gui.
     * This method is used to associate a {@link CombatController} with the view.
     *
     * @param controller the combat controller to set
     */
    void setController(CombatController controller);
}
