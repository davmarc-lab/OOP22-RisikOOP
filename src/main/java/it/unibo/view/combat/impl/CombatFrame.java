package it.unibo.view.combat.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.controller.combat.api.CombatController;
import it.unibo.view.combat.api.CombatView;

/**
 * Represents the frame for the combat phase.
 */
public class CombatFrame extends JFrame implements CombatView {

    private CombatController cc;

    /**
     * Constructs a CombatFrame object with the specified CombatController.
     *
     * @param cc the CombatController associated with the frame
     */
    public CombatFrame(final CombatController cc) {
        this.cc = cc;
        cc.setFrame(this);
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
                new CombatPanel(cc),
                new StringBuilder("Player").append(cc.getCombatPlayer().getId()).append(" troops").toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, // icon
                options,
                options[0]);
        if (result == 0) {
            cc.setCombatOutcome();
            this.dispose();
        } else {
            cc.cancelAction();
            this.dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final CombatController controller) {
        this.cc = controller;
    }
}
