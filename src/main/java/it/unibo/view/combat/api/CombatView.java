package it.unibo.view.combat.api;

import it.unibo.controller.combat.api.CombatController;

/**
 * Represents the gui for the combat phase.
 */
public interface CombatView {

    /**
     * Starts the combat popup.
     * Displays a dialog box for the combat phase, allowing the player to confirm or
     * cancel the action.
     * 
     * @param cc the controller linked to the popup
     */
    void startPopup(CombatController cc);
}
