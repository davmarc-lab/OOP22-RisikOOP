package it.unibo.view.movement;

import javax.swing.JFrame;

import it.unibo.controller.popup.api.PopupController;
import it.unibo.model.territory.api.Territory;

public class MovementPane extends JFrame {

    public MovementPane(final PopupController<Territory, Territory> pp) {
        super();
        new MovementPanel(pp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
