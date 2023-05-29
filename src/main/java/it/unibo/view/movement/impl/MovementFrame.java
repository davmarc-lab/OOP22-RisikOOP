package it.unibo.view.movement.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.controller.movement.api.MovementController;
import it.unibo.view.movement.api.MovementView;

public class MovementFrame extends JFrame implements MovementView {

    private MovementController mc;

    public MovementFrame(MovementController mc) {
        this.mc = mc;
        mc.setFrame(this);
        this.setVisible(false);
    }

    @Override
    public void startPopup() {
        String[] options = {"Confirm", "Cancel"};
        int result = JOptionPane.showOptionDialog(
                null,
                new MovementPanel(mc),
                new StringBuilder("Move troops from ").append(mc.getFirstObject().getName()).toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, // icon
                options,
                options[1]);
        if (result == 0) {
            mc.setValue();
            this.dispose();
        } else {
            this.dispose();
        }
    }

    @Override
    public void setController(MovementController controller) {
        this.mc = controller;
    }
    
}
