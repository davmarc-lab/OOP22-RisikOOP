package it.unibo.view.movement.api;

import it.unibo.controller.popup.api.ButtonObserver;

public interface MovementFrame {

    void startPopup();

    void closePopup();

    void setObserver(ButtonObserver observer);

    // BUTTONS
    void setUpButtonEnable(boolean enable);

    void setDownButtonEnable(boolean enable);

    int getInputNumberTroops();

    void setInputNumberTroops(int value);

    void setSourceTerritoryTroops(int value);
}
