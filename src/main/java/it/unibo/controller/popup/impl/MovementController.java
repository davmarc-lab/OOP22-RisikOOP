package it.unibo.controller.popup.impl;

import it.unibo.common.Pair;
import it.unibo.controller.popup.api.PopupController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.MovementPane;

public class MovementController<X, Y> extends AbstractPopupControllerImpl<X, Y> {

    public MovementController(Pair<X, Y> pair) {
        super(pair);
    }

    // @SuppressWarnings("unchecked")
    @Override
    public void startPopup() {
        try {
            if (this.getFirstTypeObject() instanceof Territory && this.getSecondTypeObject() instanceof Territory) {
                var popup = new MovementPane((PopupController<Territory, Territory>) this);
            }
        } catch (ClassCastException e) {
            // update with Logger
            e.printStackTrace();
        }
        
    }

    @Override
    public void closePopup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closePopup'");
    }

}
