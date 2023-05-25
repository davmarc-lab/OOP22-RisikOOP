package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.PopupController;

public abstract class AbstractPopupControllerImpl<X, Y> implements PopupController<X, Y> {
    
    private final Pair<X, Y> pair;
    private int value;

    public AbstractPopupControllerImpl(Pair<X, Y> pair) {
        this.pair = pair;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getFirstTypeObject() {
        return this.pair.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Y getSecondTypeObject() {
        return this.pair.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void startPopup();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void closePopup();   
}
