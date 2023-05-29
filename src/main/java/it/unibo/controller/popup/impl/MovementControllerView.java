package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.MovementController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;
import it.unibo.view.movement.impl.MovementFrame;

public class MovementControllerView implements MovementController{

    private MovementView frame;
    private final Pair<Territory, Territory> model;
    private int value;

    public MovementControllerView(Pair<Territory, Territory> pair) {
        this.model = pair;
        this.value = 1;
        this.frame = new MovementFrame(this);
    }

    @Override
    public void startPopup() {
        this.frame.startPopup();
    }

    @Override
    public MovementView getFrame() {
        return this.frame;
    }

    @Override
    public boolean isNumberValid(int value) {
        return value <= this.getFirstObject().getTroops() - 1 && value >= 1;
    }

    @Override
    public Territory getFirstObject() {
        return this.model.getX();
    }

    @Override
    public Territory getSecondObject() {
        return this.model.getY();
    }
    
    @Override
    public void setValue() {
        this.getFirstObject().addTroops(-this.value);
        this.getSecondObject().addTroops(this.value);
    }

    @Override
    public void addValue(int n) {
        if (isNumberValid(this.value + n)) {
            this.value += n;
        }
    }

    @Override
    public void setFrame(MovementView frame) {
        this.frame = frame;
    }
}
