package it.unibo.view.game_screen;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unibo.controller.api.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ButtonPanel extends JPanel {

    private static final double HEIGHT_SCALING = 0.1;
    private static final int FONT_SIZE = 12;
    private static final int NUM_OF_BUTTONS = 3;
    private static final int BORDER_SIZE = 4;

    private MainController controller;

    public ButtonPanel(final Dimension size) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Double.valueOf(size.getWidth()).intValue(),
                Double.valueOf(size.getHeight() * HEIGHT_SCALING).intValue()));
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.insets = new Insets(2, 5, 2, 5);
        cnst.gridy = 0;
        cnst.ipadx = 20;

        final JButton atkJB = this.createButton("ATTACK", size);
        final JButton movJB = this.createButton("MOVE", size);
        final JButton endJB = this.createButton("END_TURN", size);
        cnst.gridy++;
        panel.add(atkJB, cnst);
        cnst.gridy++;
        panel.add(movJB, cnst);
        cnst.gridy++;
        panel.add(endJB, cnst);

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
        this.add(panel, BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));
    }

    public void setController(final MainController controller) {
        this.controller = controller;
    }

    private JButton createButton(final String name, final Dimension dim) {
        JButton jb = new JButton(name);
        jb.setBounds(0, 0, Double.valueOf(dim.getWidth()).intValue(),
                Double.valueOf(dim.getHeight() / NUM_OF_BUTTONS).intValue());
        jb.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));

        return jb;
    }
}
