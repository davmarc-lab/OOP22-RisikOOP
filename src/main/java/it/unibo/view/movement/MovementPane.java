package it.unibo.view.movement;

import javax.swing.JOptionPane;

import it.unibo.controller.popup.api.PopupController;
import it.unibo.model.territory.api.Territory;

public class MovementPane extends JOptionPane {

    public MovementPane(final PopupController<Territory, Territory> pp) {
        super();
        JOptionPane.showConfirmDialog(null, new MovementPanel(pp),
                new StringBuilder("Discplacements Territory ").append(pp.getFirstTypeObgject().getName()).toString(),
                JOptionPane.OK_CANCEL_OPTION);
    }
}
