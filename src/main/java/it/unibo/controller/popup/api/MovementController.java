package it.unibo.controller.popup.api;

import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.MovementPane;

public interface MovementController {
    Territory getFirstObject();

    Territory getSecondObject();

    boolean isNumberValid(int value);

    boolean isConfirmButtonDisable(int value);

    int getValue();

    void setValue(int value);

    void setFrame(MovementPane frame);

    // aggiungi l'interfaccia per movementpane
    MovementPane getFrame();
    
    void startPopup();

    void closePopup();
}
