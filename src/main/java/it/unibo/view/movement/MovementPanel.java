package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.popup.PlayerPopupController;
import it.unibo.view.movement.logic.TroopsLogic;
import it.unibo.view.movement.logic.TroopsLogicImpl;

public class MovementPanel extends JPanel {

    private static final double WIDTH_PERC = 0.15;
    private static final double HEIGHT_PERC = 0.1;

    private final Dimension dimension;
    private final TroopsLogic troopsLogic;

    public MovementPanel(final MovementFrame movementFrame, final PlayerPopupController ppc) {
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.setLayout(new BorderLayout());
        this.troopsLogic = new TroopsLogicImpl(ppc.getTerritory().getTroops());
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(
                new Dimension((int) (dimension.getWidth() * WIDTH_PERC),
                        (int) (dimension.getHeight() * HEIGHT_PERC)));
        final JLabel labelText = new JLabel("How many troops do you want send to " + ppc.getTerritory().getName() + ":");
        final JButton buttonUp = new JButton("+");
        final JButton buttonDown = new JButton("-");
        final JLabel number = new JLabel(String.valueOf(ppc.getTerritory().getTroops()));

        buttonUp.addActionListener(e -> {
            if (this.troopsLogic.isIncrementationValid()) {
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), 1)));
            }
        });

        buttonDown.addActionListener(e -> {
            if (this.troopsLogic.isIncrementationValid()) {
                number.setText(String.valueOf(getIncrementedValue(Integer.parseInt(number.getText()), -1)));
            }
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
