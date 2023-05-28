package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.ButtonObserver;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementFrame;

public class MovementControllerView implements ButtonObserver{

    private MovementFrame frame;
    private final Pair<Territory, Territory> model;
    private int value;

    public MovementControllerView(Pair<Territory, Territory> pair, MovementFrame frame) {
        this.model = pair;
        this.frame = frame;
        this.frame.setObserver(this);
        this.value = this.frame.getInputNumberTroops();
        this.frame.startPopup();
    }

    private void updateView() {
        this.frame.setInputNumberTroops(this.value);
        this.frame.setSourceTerritoryTroops(this.getFirstObject().getTroops() - this.value);
    }
  
    private boolean isNumberValid(int value) {
        return value <= this.getFirstObject().getTroops() - 1 && value >= 1;
    }

    private Territory getFirstObject() {
        return this.model.getX();
    }

    private Territory getSecondObject() {
        return this.model.getY();
    }
    
    private void setValue(int value) {
        this.getFirstObject().addTroops(-value);
        this.getSecondObject().addTroops(value);
    }

    @Override
    public void addValue(int n) {
        this.frame.setUpButtonEnable(this.value + n < this.getFirstObject().getTroops() - 1);
        this.frame.setDownButtonEnable(this.value + n > 1);
        if (isNumberValid(this.value + n)) {
            this.value += n;
            updateView();
        }
    }

    @Override
    public void confirmMovement() {
        this.setValue(this.value);
        this.frame.closePopup();
    }

    @Override
    public void cancelMovement() {
        this.frame.closePopup();
    }
}
