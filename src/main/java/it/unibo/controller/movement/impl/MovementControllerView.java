package it.unibo.controller.movement.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.controller.movement.api.MovementController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.api.MovementView;
import it.unibo.view.movement.impl.MovementFrame;

/**
 * Implementation of {@code MovementController}.
 * It use as field a {@code Pair} containig the source and the
 * destination of the troops.
 */
public class MovementControllerView implements MovementController {

    private final MovementView frame;
    private final Pair<Territory, Territory> model;
    private int value;
    private Optional<Integer> finalResult = Optional.empty();
    private boolean isActionRunning = true;

    /**
     * Constructor with the model parts.
     * 
     * @param model the model objects used
     */
    public MovementControllerView(final Pair<Territory, Territory> model) {
        this.model = model;
        this.value = 1;
        this.frame = new MovementFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPopup() {
        this.frame.startPopup(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNumberValid(final int value) {
        return value <= this.getFirstTerritory().getTroops() - 1 && value >= 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getFirstTerritory() {
        return this.model.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getSecondTerritory() {
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
    public void cancelAction() {
        this.isActionRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActionRunning() {
        return this.isActionRunning;
    }
}
