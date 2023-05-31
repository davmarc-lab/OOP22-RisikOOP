package it.unibo.model.movement.impl;

import javax.swing.JOptionPane;

import it.unibo.controller.movement.api.MovementController;
import it.unibo.view.movement.api.MovementView;

public class MovementErrorDialog implements MovementView {

    @Override
    public void startPopup() {
        JOptionPane.showMessageDialog(null, "Invalid troops of source territory");
    }

    @Override
    public void setController(MovementController controller) {
        // logger here
        System.out.println("Impossibile settare un controller");
    }

}
