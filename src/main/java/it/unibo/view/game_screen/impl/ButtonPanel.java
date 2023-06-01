package it.unibo.view.game_screen.impl;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.controller.api.MainController;
import it.unibo.view.game_screen.api.ButtonZone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Implementation of ButtonZone interface.
 */
public class ButtonPanel extends JPanel implements ButtonZone {

    private static final double HEIGHT_SCALING = 0.15;
    private static final double BUTTON_SCALING = 0.08;
    private static final int FONT_SIZE = 12;
    private static final int NUM_OF_BUTTONS = 3;
    private static final int BORDER_SIZE = 4;
    private static final String TITLE_LABEL = "PLAYER ACTIONS";

    private MainController controller;

    /**
     * Creates the panel with the action buttons inside the sidebar.
     * 
     * @param size the size of the sidebar
     */
    public ButtonPanel(final Dimension size) {
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel(TITLE_LABEL, SwingConstants.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Double.valueOf(size.getWidth()).intValue(),
                Double.valueOf(size.getHeight() * HEIGHT_SCALING).intValue()));
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        final int n1 = 2;
        final int n2 = 5;
        cnst.insets = new Insets(n1, n2, n1, n2);
        cnst.gridy = 0;
        cnst.ipadx = Double.valueOf(panel.getPreferredSize().getWidth() * BUTTON_SCALING).intValue();
        cnst.ipady = Double.valueOf(panel.getPreferredSize().getHeight() * BUTTON_SCALING).intValue();

        final JButton atkJB = this.createButton("ATTACK", size);
        final JButton movJB = this.createButton("MOVE", size);
        final JButton endJB = this.createButton("END_TURN", size);
        final JButton randJB = this.createButton("RANDOM", size);
        cnst.gridy++;
        panel.add(atkJB, cnst);
        cnst.gridy++;
        panel.add(movJB, cnst);
        cnst.gridy++;
        panel.add(endJB, cnst);
        cnst.gridy++;
        panel.add(randJB, cnst);

        atkJB.addActionListener(e -> {
            this.controller.switchToCombat();
        });
        movJB.addActionListener(e -> {
            this.controller.switchToMovement();
        });
        endJB.addActionListener(e -> {
            this.controller.endTurn();
        });
        randJB.addActionListener(e -> {
            this.controller.randomize();
            randJB.setEnabled(false);
        });

        panel.setOpaque(false);
        this.add(label, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
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
