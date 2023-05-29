package it.unibo.view.combat.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.combat.api.CombatController;

public class CombatPanel extends JPanel {

    final JButton buttonUp = new JButton("+");
    final JButton buttonDown = new JButton("-");
    final JLabel number = new JLabel(String.valueOf(1));

    public CombatPanel(final CombatController cc) {

        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();

        this.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);

        final JLabel labelText = new JLabel(new StringBuilder("How many troops do you want to use from ")
                .append(cc.getSecondObject().getName()).append(": ").toString());

        buttonUp.addActionListener(e -> {
            if (cc.isNumberValid(Integer.parseInt(number.getText()) + 1)) {
                cc.addValue(1);
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
            }
            updateView(cc);
        });

        buttonDown.addActionListener(e -> {
            if (cc.isNumberValid(Integer.parseInt(number.getText()) - 1)) {
                cc.addValue(-1);
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

    private void updateView(final CombatController cc) {
        buttonUp.setEnabled(cc.isNumberValid(Integer.parseInt(number.getText()) + 1));
        buttonDown.setEnabled(cc.isNumberValid(Integer.parseInt(number.getText()) - 1));
    }

    private int getIncrementedValue(final int val, final int offset) {
        return val + offset;
    }
}