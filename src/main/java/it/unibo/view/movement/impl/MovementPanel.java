package it.unibo.view.movement.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.movement.api.MovementController;

/**
 * Represents a graphical panel to move the
 * troops between territories.
 */
public class MovementPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int LEFT_RIGHT_BORDER = 5;
    private static final int TOP_BOTTOM_BORDER = 2;

    /**
     * The number of troops in the source territory.
     */
    private final int source;

    /**
     * Increment button.
     */
    private final JButton buttonUp = new JButton("+");

    /**
     * Decrement button.
     */
    private final JButton buttonDown = new JButton("-");

    /**
     * Label displaying the current number of troops.
     */
    private final JLabel number = new JLabel(String.valueOf(1));

    /**
     * Label displaying the current status of the movement.
     */
    private final JLabel currentTerritoryStatus = new JLabel();

    /**
     * Constructor that creates a {@code MovementPanel} object.
     * 
     * @param mc movement controller
     */
    public MovementPanel(final MovementController mc) {
        source = mc.getFirstTerritory().getTroops();

        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();

        this.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(TOP_BOTTOM_BORDER, LEFT_RIGHT_BORDER, TOP_BOTTOM_BORDER, LEFT_RIGHT_BORDER);

        final JLabel labelText = new JLabel(new StringBuilder("How many troops do you want send to ")
                .append(mc.getSecondTerritory().getName()).append(':').toString());
        currentTerritoryStatus.setText(getCurrentStatus(mc));

        buttonUp.addActionListener(e -> {
            if (mc.isNumberValid(Integer.parseInt(number.getText()) + 1)) {
                mc.addValue(1);
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
            }
            updateView(mc);
        });

        buttonDown.addActionListener(e -> {
            if (mc.isNumberValid(Integer.parseInt(number.getText()) - 1)) {
                mc.addValue(-1);
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), -1)));
            }
            updateView(mc);
        });

        valuesPanel.add(buttonUp, cnst);
        cnst.gridy++;
        valuesPanel.add(number, cnst);
        cnst.gridy++;
        valuesPanel.add(buttonDown, cnst);
        this.add(labelText, BorderLayout.WEST);
        this.add(valuesPanel, BorderLayout.CENTER);
        this.add(currentTerritoryStatus, BorderLayout.SOUTH);
    }

    /**
     * Updates the view based on the MovementController's state.
     * Enables or disables the increment and decrement buttons based on the
     * validity.
     * 
     * @param mc the MovementController associated with this panel.
     */
    private void updateView(final MovementController mc) {
        buttonUp.setEnabled(mc.isNumberValid(Integer.parseInt(number.getText()) + 1));
        buttonDown.setEnabled(mc.isNumberValid(Integer.parseInt(number.getText()) - 1));
        currentTerritoryStatus.setText(getCurrentStatus(mc));
    }

    /**
     * Calculates the incremented value by adding the offset to the given value.
     * 
     * @param val    The original value.
     * @param offset The offset value to be added.
     * @return the incremented value.
     */
    private int getIncrementedValue(final int val, final int offset) {
        return val + offset;
    }

    /**
     * Gets the current status of the movement.
     * 
     * @param mc the MovementController associated with this panel.
     * @return the current status of the movement.
     */
    private String getCurrentStatus(final MovementController mc) {
        return new StringBuilder(mc.getFirstTerritory().getName())
                .append(" troops remaining: ").append(this.source - Integer.parseInt(this.number.getText())).toString();
    }
}
