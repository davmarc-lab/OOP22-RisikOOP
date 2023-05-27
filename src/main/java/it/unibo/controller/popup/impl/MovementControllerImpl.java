package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.MovementController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.MovementPane;

public class MovementControllerImpl implements MovementController {

    private MovementPane frame;
    private final Pair<Territory, Territory> pair;
    private int value;

    public MovementControllerImpl(Pair<Territory, Territory> pair) {
        this.pair = pair;
    }

    @Override
    public Territory getFirstObject() {
        return this.pair.getX();
    }
    
    @Override
    public Territory getSecondObject() {
        return this.pair.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        if (this.isNumberValid(value)) {
            this.getFirstObject().addTroops(-value);
            this.getSecondObject().addTroops(value);
        }
    }

    @Override
    public void setFrame(MovementPane frame) {
        this.frame = frame;
    }

    @Override
    public MovementPane getFrame() {
        return this.frame;
    }

    @Override
    public boolean isNumberValid(int value) {
        return value <= this.getFirstObject().getTroops() - 1 && value >= 1;
    }

    @Override
    public boolean isConfirmButtonDisable(int value) {
        return value == 0;
    }

    @Override
    public void startPopup() {
        this.frame = new MovementPane(this);
    }

    @Override
    public void closePopup() {
        this.frame.dispose();
    }

}
