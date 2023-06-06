package it.unibo.view.combat.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.controller.combat.api.CombatController;
import it.unibo.view.combat.api.CombatView;

/**
 * Implementation of the {@link CombatView} interface.
 * Represents the frame for the combat phase.
 */
public class CombatFrame extends JFrame implements CombatView {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPopup(final CombatController cc) {
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
}
