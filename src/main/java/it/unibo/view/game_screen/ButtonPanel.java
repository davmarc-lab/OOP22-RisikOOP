package it.unibo.view.game_screen;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.controller.api.MainController;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ButtonPanel extends JPanel {

    private MainController controller;

    public ButtonPanel(final Dimension size) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 0, 10));
        JButton atkJB = new JButton("ATTACK");
        JButton movJB = new JButton("MOVE");
        JButton endJB = new JButton("END TURN");
        panel.add(atkJB);
        panel.add(movJB);
        panel.add(endJB);

        atkJB.addActionListener(e -> {
            this.controller.switchToCombat();
        });
        movJB.addActionListener(e -> {
            this.controller.switchToMovement();
        });
        endJB.addActionListener(e -> {
            this.controller.endTurn();
        });

        panel.setOpaque(false);
        this.add(panel);
        this.setBackground(Color.RED);
    }

    public void setController (final MainController controller) {
        this.controller = controller;
    }

}
