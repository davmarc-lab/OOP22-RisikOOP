package it.unibo.controller.popup.api;

import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementFrame;

public interface MovementController {
    Territory getFirstObject();

    Territory getSecondObject();

    boolean isNumberValid(int value);

    boolean isConfirmButtonDisable(int value);

    int getValue();

    void setValue(int value);

    void setFrame(MovementFrame frame);

    // aggiungi l'interfaccia per movementpane
    MovementFrame getFrame();
    
    void startPopup();

    void closePopup();
}
