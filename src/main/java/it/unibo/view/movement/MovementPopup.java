package it.unibo.view.movement;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.view.movement.api.MovementFrame;

public class MovementPopup extends JFrame implements MovementFrame {

    private JPanel panel;

    private String source = "";
    private String destin = "";
    private int sourceTroops;
    final JButton buttonUp = new JButton("+");
    final JButton buttonDown = new JButton("-");
    final JLabel inputNumber = new JLabel(String.valueOf(1));
    final JLabel currentTerritoryStatus = new JLabel();
    final JButton confirmButton = new JButton("Send");
    final JButton cancelButton = new JButton("Cancel");
    final JLabel labelText = new JLabel();

    public MovementPopup(final String destin, final String source, final int sourceTroops) {
        super();
        this.destin = destin;
        this.source = source;
        this.sourceTroops = sourceTroops;
        this.createPanel();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.add(this.panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void createPanel() {
        this.panel = new JPanel();

        labelText.setText(new StringBuilder("How many troops do you want send to ")
                .append(destin).append(":").toString());

        currentTerritoryStatus.setText(
                new StringBuilder(source).append(" troops remaining: ").append(sourceTroops).toString());
        final JPanel valuesPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        this.panel.setLayout(new BorderLayout());
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
        this.panel.add(labelText, BorderLayout.WEST);
        this.panel.add(valuesPanel, BorderLayout.CENTER);
        this.panel.add(currentTerritoryStatus, BorderLayout.SOUTH);
        this.panel.add(confirmButton, BorderLayout.SOUTH);

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
    public JButton getButtonUp() {
        return this.buttonUp;
    }

    @Override
    public void addActionListenerButtonUp(ActionListener listener) {
        this.buttonUp.addActionListener(listener);
    }

    @Override
    public JButton getButtonDown() {
        return this.buttonDown;
    }

    @Override
    public void addActionListenerButtonDown(ActionListener listener) {
        this.buttonDown.addActionListener(listener);
    }

    @Override
    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    @Override
    public void addActionListenerConfirmButton(ActionListener listener) {
        this.confirmButton.addActionListener(listener);
    }

    @Override
    public JButton getCancelButton() {
        return this.cancelButton;
    }

    @Override
    public void addActionListenerCancelButton(ActionListener listener) {
        this.cancelButton.addActionListener(listener);
    }

    @Override
    public int getSourceTroops() {
        return this.sourceTroops;
    }

    @Override
    public void setSourceTroops(int value) {
        this.sourceTroops = value;
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
    public String getCurrentTerritoryStatus() {
        return this.currentTerritoryStatus.getText();
    }

    @Override
    public void setCurrentTerritoryStatus(String status) {
        this.currentTerritoryStatus.setText(status);
    }

    @Override
    public String getDestinationTerritory() {
        return this.destin;
    }

    @Override
    public void setDestinationTerritory(String name) {
        this.destin = name;
    }

    @Override
    public String getSourceTerritory() {
        return this.source;
    }

    @Override
    public void setSourceTerritory(String name) {
        this.source = name;
    }
}
