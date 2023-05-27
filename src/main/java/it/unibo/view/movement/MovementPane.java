package it.unibo.view.movement;

import javax.swing.JFrame;

import it.unibo.controller.popup.api.MovementController;

public class MovementPane extends JFrame {

    public MovementPane(final MovementController mc) {
        super();
        mc.setFrame(this);
        new MovementPanel(mc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
