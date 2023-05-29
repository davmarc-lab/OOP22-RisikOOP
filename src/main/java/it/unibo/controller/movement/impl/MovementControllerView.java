package it.unibo.controller.movement.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.controller.movement.api.MovementController;
import it.unibo.model.movement.impl.MovementErrorDialog;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;
import it.unibo.view.movement.impl.MovementFrame;

public class MovementControllerView implements MovementController{

    private MovementView frame;
    private final Pair<Territory, Territory> model;
    private int value;
    private Optional<Integer> finalResult = Optional.empty();

    public MovementControllerView(Pair<Territory, Territory> pair) {
        this.model = pair;
        if (this.model.getX().getTroops() > 1) {
            this.value = 1;
            this.frame = new MovementFrame(this);
        } else {
            // logger here
            this.frame = new MovementErrorDialog();
        }
        
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
        if (isNumberValid(this.value)) {
            this.finalResult = Optional.of(this.value);
        }
    }

    @Override
    public int getFinalResult() {
        if (this.finalResult.isPresent()) {
            return this.finalResult.get();
        }
        // logger here
        return 0;
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
