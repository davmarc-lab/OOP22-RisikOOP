package it.unibo.view.movement.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.controller.movement.api.MovementController;
import it.unibo.view.movement.api.MovementView;

/**
 * Implementation of {@link MovementView} class represent a graphical frame displaying the
 * movement view.
 */
public class MovementFrame extends JFrame implements MovementView {

    private MovementController mc;

    /**
     * Constructor that creates a {@code MovementFrame} object.
     * 
     * @param mc movement controller
     */
    public MovementFrame(final MovementController mc) {
        this.mc = mc;
        mc.setFrame(this);
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPopup() {
        final String[] options = { "Confirm", "Cancel" };
        final int result = JOptionPane.showOptionDialog(
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final MovementController controller) {
        this.mc = controller;
    }

}
