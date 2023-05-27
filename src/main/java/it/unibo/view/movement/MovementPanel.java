package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovementPanel extends JPanel {

    public MovementPanel(final String source, final String destin, final int sourceTroops, final JButton buttonUp,
            final JButton buttonDown, final JLabel inputNumber, final JLabel currentTerritoryStatus,
            final JButton confirmButton, final JButton cancelButton, final JLabel labelText) {
        // settare di la
        labelText.setText(new StringBuilder("How many troops do you want send to ")
                .append(destin).append(":").toString());

        currentTerritoryStatus.setText(
                new StringBuilder(source).append(" troops remaining: ").append(sourceTroops).toString());
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.setLayout(new BorderLayout());
        buttonDown.setFocusable(false);
        buttonUp.setFocusable(false);
        confirmButton.setFocusable(false);
        cancelButton.setFocusable(false);
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);
        valuesPanel.add(buttonUp, cnst);
        cnst.gridy++;
        valuesPanel.add(inputNumber, cnst);
        cnst.gridy++;
        valuesPanel.add(buttonDown, cnst);
        this.add(labelText, BorderLayout.WEST);
        this.add(valuesPanel, BorderLayout.CENTER);
        this.add(currentTerritoryStatus, BorderLayout.SOUTH);
        this.add(confirmButton, BorderLayout.SOUTH);
    }
}
