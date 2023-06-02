package it.unibo.controller.movement.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.controller.movement.api.MovementController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;
import it.unibo.view.movement.impl.MovementFrame;

/**
 * Implemetnation of {@code MovementController}.
 */
public class MovementControllerView implements MovementController {

    private MovementView frame;
    private final Pair<Territory, Territory> model;
    private int value;
    private Optional<Integer> finalResult = Optional.empty();

    /**
     * Constructor with the model parts.
     * 
     * @param model the model objects used
     */
    public MovementControllerView(final Pair<Territory, Territory> model) {
        this.model = model;
        if (this.model.getX().getTroops() > 1) {
            this.value = 1;
            this.frame = new MovementFrame(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPopup() {
        this.frame.startPopup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementView getFrame() {
        return this.frame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNumberValid(final int value) {
        return value <= this.getFirstObject().getTroops() - 1 && value >= 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getFirstObject() {
        return this.model.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getSecondObject() {
        return this.model.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue() {
        if (isNumberValid(this.value)) {
            this.finalResult = Optional.of(this.value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFinalResult() {
        if (this.finalResult.isPresent()) {
            return this.finalResult.get();
        }
        // logger here
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addValue(final int n) {
        if (isNumberValid(this.value + n)) {
            this.value += n;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrame(final MovementView frame) {
        this.frame = frame;
    }
}
