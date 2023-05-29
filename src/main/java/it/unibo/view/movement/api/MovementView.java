package it.unibo.view.movement.api;

import it.unibo.controller.movement.api.MovementController;

public interface MovementView {

    void startPopup();

    void setController(MovementController controller);
}
