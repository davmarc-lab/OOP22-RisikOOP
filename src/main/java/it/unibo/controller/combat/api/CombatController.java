package it.unibo.controller.combat.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.combat.api.CombatView;

public interface CombatController {

    void startPopup();

    CombatView getFrame();

    void setFrame(CombatView frame);

    void addValue(int n);

    boolean isNumberValid(int value);

    void setValue();

    int getFinalResult();

    Player getFirstObject();

    Territory getSecondObject();
}
