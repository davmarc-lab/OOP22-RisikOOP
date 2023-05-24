package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.popup.api.PopupController;
import it.unibo.model.territory.api.Territory;

public class MovementPanel extends JPanel {

    public MovementPanel(final PopupController<Territory, Territory> ppc) {
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);
        final JLabel labelText = new JLabel(
                "How many troops do you want send to " + ppc.getSecondTypeObject().getName() + ":");
        final JButton buttonUp = new JButton("+");
        final JButton buttonDown = new JButton("-");
        final JLabel number = new JLabel(String.valueOf(ppc.getSecondTypeObject().getTroops()));
        buttonUp.addActionListener(e -> {
            number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
            this.validate();
        });

        buttonDown.addActionListener(e -> {
            number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), -1)));
        });
        
        valuesPanel.add(buttonUp, cnst);
        cnst.gridy++;
        valuesPanel.add(number, cnst);
        cnst.gridy++;
        valuesPanel.add(buttonDown, cnst);
        this.add(labelText, BorderLayout.WEST);
        this.add(valuesPanel, BorderLayout.CENTER);
    }

    private int getIncrementedValue(final int number, final int offset) {
        return number + offset;
    }
}
