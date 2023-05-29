package it.unibo.controller.movement.api;

import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;

public interface MovementController {

    void startPopup();

    MovementView getFrame();

    void setFrame(MovementView frame);

    void addValue(int n);

    boolean isNumberValid(int value);

    void setValue();

    Territory getFirstObject();
    
    Territory getSecondObject();
}
