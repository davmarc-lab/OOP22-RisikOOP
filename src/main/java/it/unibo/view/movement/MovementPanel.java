package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.controller.popup.api.PopupController;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.movement.logic.TroopsLogic;
import it.unibo.view.movement.logic.TroopsLogicImpl;

public class MovementPanel extends JPanel {

    private final TroopsLogic logic;

    public MovementPanel(final PopupController<Territory, Territory> pp) {
        this.logic = new TroopsLogicImpl(pp.getFirstTypeObject().getTroops());
        final JButton confirmButton = new JButton("Confirm");
        final JButton cancelButton = new JButton("Cancel");
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();

        this.setLayout(new BorderLayout());
        confirmButton.setEnabled(false);
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);

        final JLabel labelText = new JLabel(new StringBuilder("How many troops do you want send to ")
                .append(pp.getSecondTypeObject().getName()).append(":").toString());
        final JLabel currentTerritoryStatus = new JLabel(getCurrentTerritoryStatus(pp.getFirstTypeObject()));
        final JButton buttonUp = new JButton("+");
        final JButton buttonDown = new JButton("-");
        final JLabel number = new JLabel(String.valueOf(0));

        buttonUp.addActionListener(e -> {
            if (this.logic.isIncrementationValid(Integer.parseInt(number.getText()))) {
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
                pp.getFirstTypeObject().addTroops(-1);
                currentTerritoryStatus.setText(getCurrentTerritoryStatus(pp.getFirstTypeObject()));
                confirmButton.setEnabled(this.logic.isNumberZero(Integer.parseInt(number.getText())) ? false : true);
            }
        });

        buttonDown.addActionListener(e -> {
            if (this.logic.isDecrementationValid(Integer.parseInt(number.getText()))) {
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), -1)));
                pp.getFirstTypeObject().addTroops(1);
                currentTerritoryStatus.setText(getCurrentTerritoryStatus(pp.getFirstTypeObject()));
                confirmButton.setEnabled(this.logic.isNumberZero(Integer.parseInt(number.getText())) ? false : true);
            }
        });

        valuesPanel.add(buttonUp, cnst);
        cnst.gridy++;
        valuesPanel.add(number, cnst);
        cnst.gridy++;
        valuesPanel.add(buttonDown, cnst);
        this.add(labelText, BorderLayout.WEST);
        this.add(valuesPanel, BorderLayout.CENTER);
        this.add(currentTerritoryStatus, BorderLayout.SOUTH);

        JOptionPane.showOptionDialog(
                null,
                this,
                new StringBuilder("Move troops from ").append(pp.getFirstTypeObject().getName()).toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,       //icon
                new Object[] {confirmButton, cancelButton},
                cancelButton
                );

    }

    private int getIncrementedValue(final int number, final int offset) {
        return number + offset;
    }

    private String getCurrentTerritoryStatus(final Territory t) {
        return new StringBuilder(t.getName())
                .append(" troops remaining: ").append(t.getTroops()).toString();
    }
}
