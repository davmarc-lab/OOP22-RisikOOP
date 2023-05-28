package it.unibo.view.movement.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unibo.controller.popup.api.ButtonObserver;
import it.unibo.view.movement.api.MovementFrame;

public class MovementPopup extends JFrame implements MovementFrame {

    private JPanel panel;
    private String source = "";
    private String destin = "";
    private int sourceTroops;
    private final JButton buttonUp = new JButton("+");
    private final JButton buttonDown = new JButton("-");
    private final JLabel currentTerritoryStatus = new JLabel();
    private final JLabel inputNumber = new JLabel(String.valueOf(1));
    private ButtonObserver observer;

    public MovementPopup(final String source, final String destin, final int sourceTroops) {
        super(new StringBuilder("Displacements from ").append(source).toString());
        this.destin = destin;
        this.source = source;
        this.sourceTroops = sourceTroops;
        this.setPreferredSize(new Dimension(500, 200));
        this.createPanel();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.add(this.panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createPanel() {
        final JButton confirmButton = new JButton("Send");
        final JButton cancelButton = new JButton("Cancel");
        final JLabel labelText = new JLabel();
        final JPanel closePanel = new JPanel(new FlowLayout());
        final JPanel inputPanel = new JPanel(new GridBagLayout());
        final JPanel pan = new JPanel(new BorderLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.panel = new JPanel();
        this.panel.setBorder(new LineBorder(new Color(0, 0, 0, 0), 5));

        labelText.setText(new StringBuilder("How many troops do you want send to ")
                .append(this.destin).append(":").toString());
        this.panel.setLayout(new BorderLayout());
        this.setSourceTerritoryTroops(this.sourceTroops);
        this.buttonUp.addActionListener(e -> observer.addValue(1));
        this.buttonDown.addActionListener(e -> observer.addValue(-1));
        confirmButton.addActionListener(e -> observer.confirmMovement());
        cancelButton.addActionListener(e -> observer.cancelMovement());

        cnst.gridy = 0;
        cnst.insets = new Insets(2, 5, 2, 5);
        inputPanel.add(buttonUp, cnst);
        cnst.gridy++;
        inputPanel.add(inputNumber, cnst);
        cnst.gridy++;
        inputPanel.add(buttonDown, cnst);
        closePanel.add(confirmButton);
        closePanel.add(cancelButton);
        pan.add(inputPanel, BorderLayout.EAST);
        pan.add(labelText, BorderLayout.WEST);
        pan.add(currentTerritoryStatus, BorderLayout.SOUTH);

        this.panel.add(pan, BorderLayout.NORTH);
        this.panel.add(closePanel, BorderLayout.SOUTH);
    }

    @Override
    public void startPopup() {
        this.setVisible(true);
    }

    @Override
    public void closePopup() {
        this.dispose();
    }

    @Override
    public void setObserver(ButtonObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setUpButtonEnable(boolean enable) {
        this.buttonUp.setEnabled(enable);
    }

    @Override
    public void setDownButtonEnable(boolean enable) {
        this.buttonDown.setEnabled(enable);
    }

    @Override
    public int getInputNumberTroops() {
        return Integer.parseInt(this.inputNumber.getText());
    }

    @Override
    public void setInputNumberTroops(int value) {
        this.inputNumber.setText(String.valueOf(value));
    }

    @Override
    public void setSourceTerritoryTroops(int value) {
        this.currentTerritoryStatus.setText(
                new StringBuilder(this.source).append(" troops remaining: ").append(value).toString());
    }
}
