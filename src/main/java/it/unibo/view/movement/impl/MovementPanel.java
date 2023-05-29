package it.unibo.view.movement.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.popup.api.MovementController;

public class MovementPopup extends JPanel {
    
    private int source;
    final JButton buttonUp = new JButton("+");
    final JButton buttonDown = new JButton("-");
    final JLabel number = new JLabel(String.valueOf(1));
    final JLabel currentTerritoryStatus = new JLabel();

    public MovementPopup(final MovementController mc) {
        source = mc.getFirstObject().getTroops();

        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();

        this.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);

        final JLabel labelText = new JLabel(new StringBuilder("How many troops do you want send to ")
                .append(mc.getSecondObject().getName()).append(":").toString());
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

    private void updateView(final MovementController mc) {
        buttonUp.setEnabled(mc.isNumberValid(Integer.parseInt(number.getText()) + 1));
        buttonDown.setEnabled(mc.isNumberValid(Integer.parseInt(number.getText()) - 1));
        currentTerritoryStatus.setText(getCurrentStatus(mc));
    }

    private int getIncrementedValue(final int val, final int offset) {
        return val + offset;
    }

    private String getCurrentStatus(final MovementController mc) {
        return new StringBuilder(mc.getFirstObject().getName())
                .append(" troops remaining: ").append(this.source - Integer.parseInt(this.number.getText())).toString();
    }

}