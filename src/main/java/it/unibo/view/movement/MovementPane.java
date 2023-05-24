package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.controller.popup.PlayerPopupController;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.impl.PlayerBuilderImpl;
import it.unibo.model.territory.impl.TerritoryImpl;
import it.unibo.view.movement.logic.TroopsLogic;
import it.unibo.view.movement.logic.TroopsLogicImpl;

public class MovementPane extends JOptionPane {

    private JPanel panel = new JPanel();
    private final TroopsLogic logic;

    public MovementPane(final PlayerPopupController ppc) {
        super();
        this.logic = new TroopsLogicImpl(ppc.getTerritory().getTroops());
        JOptionPane.showConfirmDialog(null, this.getPanel(ppc),
                new StringBuilder("Discplacements Player ").append(ppc.getPlayer().getId()).toString(),
                JOptionPane.OK_CANCEL_OPTION);
    }

    private JPanel getPanel(final PlayerPopupController ppc) {
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.panel.setLayout(new BorderLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);
        final JLabel labelText = new JLabel(
                "How many troops do you want send to " + ppc.getTerritory().getName() + ":");
        final JButton buttonUp = new JButton("+");
        final JButton buttonDown = new JButton("-");
        final JLabel number = new JLabel(String.valueOf(ppc.getTerritory().getTroops()));

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
        this.panel.add(labelText, BorderLayout.WEST);
        this.panel.add(valuesPanel, BorderLayout.CENTER);
        return this.panel;
    }

    private int getIncrementedValue(final int number, final int offset) {
        return number + offset;
    }

    public static void main(String[] args) {
        var t = new TerritoryImpl("Cina");
        t.addTroops(2);
        new MovementPane(new PlayerPopupController(
                PlayerBuilderImpl.newBuilder().id(1).objective(new ObjectiveImpl()).build(), t));
    }

}
