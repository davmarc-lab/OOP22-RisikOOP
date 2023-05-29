package it.unibo.view.combat.api;

import it.unibo.controller.combat.api.CombatController;

public interface CombatView {

    void startPopup();

    void setController(CombatController controller);
}
