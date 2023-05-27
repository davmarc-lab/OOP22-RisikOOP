package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.MovementController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementFrame;

public class MovementControllerImpl implements MovementController {

    private MovementFrame frame;
    private final Pair<Territory, Territory> model;
    private int value;

    public MovementControllerImpl(Pair<Territory, Territory> pair, MovementFrame frame) {
        this.model = pair;
        this.frame = frame;
        this.value = this.frame.getInputNumberTroops();
            this.frame.addActionListenerButtonUp(e -> {
                if (this.isNumberValid(value + 1)) {
                    this.incValue(1);
                    frame.setInputNumberTroops(this.value);
                    updateView();
                }
            });

        this.frame.addActionListenerButtonDown(e -> {
            if (this.isNumberValid(value - 1)) {
                this.incValue(-1);
                this.frame.setInputNumberTroops(this.value);
                updateView();
            }
        });

        this.frame.addActionListenerConfirmButton(e -> {
            this.setValue(this.value);
            this.frame.closePopup();
        });

        this.frame.addActionListenerCancelButton(e -> {
            this.closePopup();
        });
    }

    private void updateView() {
        this.frame.getButtonUp().setEnabled(true);
        this.frame.getButtonDown().setEnabled(true);
        this.frame.setInputNumberTroops(this.value);
    }

    private void incValue(int n) {
        this.value += n;
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
            System.out.println(this.getFirstObject());
            System.out.println(this.getSecondObject());
        }
    }

    @Override
    public void setFrame(MovementFrame frame) {
        this.frame = frame;
    }

    @Override
    public MovementFrame getFrame() {
        return this.frame;
    }

    @Override
    public boolean isConfirmButtonDisable(int value) {
        return value == 0;
    }

    @Override
    public void startPopup() {
        this.frame.startPopup();
    }

    @Override
    public void closePopup() {
        this.frame.closePopup();
    }
}
