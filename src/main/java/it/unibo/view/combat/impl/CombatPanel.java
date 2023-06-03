package it.unibo.view.combat.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.combat.api.CombatController;

/**
 * The CombatPanel class represents the panel for combat interactions.
 * It provides buttons to increment and decrement troop numbers and displays the
 * current number of troops.
 * 
 * This panel uses {@link CombatController} to handle the combat logic.
 */
public class CombatPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int TOP_BOTTOM_BORDER = 2;
    private static final int LEFT_RIGHT_BORDER = 5;

    private final JButton buttonUp = new JButton("+");
    private final JButton buttonDown = new JButton("-");
    private final JLabel number = new JLabel(String.valueOf(1));

    /**
     * Constructs a CombatPanel object with the given CombatController.
     * 
     * @param cc The CombatController associated with this panel.
     */
    public CombatPanel(final CombatController cc) {

        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();

        this.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(TOP_BOTTOM_BORDER, LEFT_RIGHT_BORDER, TOP_BOTTOM_BORDER, LEFT_RIGHT_BORDER);

        final JLabel labelText = new JLabel(new StringBuilder("How many troops do you want to use from ")
                .append(cc.getCombatTerritory().getName()).append(": ").toString());

        buttonUp.addActionListener(e -> {
            if (cc.isNumberValid(Integer.parseInt(number.getText()) + 1)) {
                cc.addTroops(1);
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
            }
            updateView(cc);
        });

        buttonDown.addActionListener(e -> {
            if (cc.isNumberValid(Integer.parseInt(number.getText()) - 1)) {
                cc.addTroops(-1);
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), -1)));
            }
            updateView(cc);
        });

        valuesPanel.add(buttonUp, cnst);
        cnst.gridy++;
        valuesPanel.add(number, cnst);
        cnst.gridy++;
        valuesPanel.add(buttonDown, cnst);
        this.add(labelText, BorderLayout.WEST);
        this.add(valuesPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the view based on the CombatController's state.
     * Enables or disables the increment and decrement buttons based on the validity
     * of the troop number.
     * 
     * @param cc The CombatController associated with this panel.
     */
    private void updateView(final CombatController cc) {
        buttonUp.setEnabled(cc.isNumberValid(Integer.parseInt(number.getText()) + 1));
        buttonDown.setEnabled(cc.isNumberValid(Integer.parseInt(number.getText()) - 1));
    }

    /**
     * Calculates the incremented value by adding the offset to the given value.
     * 
     * @param val    The original value.
     * @param offset The offset value to be added.
     * @return The incremented value.
     */
    private int getIncrementedValue(final int val, final int offset) {
        return val + offset;
    }
}
