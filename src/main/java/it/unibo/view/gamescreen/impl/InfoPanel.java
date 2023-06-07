package it.unibo.view.gamescreen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.view.gamescreen.api.InfoZone;

/**
 * Implementation of {@link InfoZone} interface.
 * Defines the area inside the side zone where player
 * information is shown.
 */
public class InfoPanel extends JPanel implements InfoZone {

    private static final long serialVersionUID = 1L;

    private static final String INFO_LABEL = "PLAYER INFO";
    private static final String OBJECTIVE_TITLE = "OBJECTIVE: ";
    private static final int BORDER_SIZE = 4;
    private static final double INFO_PANEL_HEIGHT_SCALING = 0.3;

    private final transient MainController controller;
    /**
     * Label containing the player's ID.
     */
    private final JLabel pLabel;

    /**
     * Label containing the player's color.
     */
    private final JLabel cLabel;

    /**
     * Text area containing the player's objective.
     */
    private final JTextArea objText;

    /**
     * Constructs an {@code InfoPanel} with the player's informations inside the
     * sidebar.
     * 
     * @param dimension  the panel dimension
     * @param controller the main controller
     */
    public InfoPanel(final Dimension dimension, final MainController controller) {
        super();
        this.controller = controller.getCopy();
        this.setPreferredSize(new Dimension(Double.valueOf(dimension.getWidth()).intValue(),
                Double.valueOf(dimension.getHeight() * INFO_PANEL_HEIGHT_SCALING).intValue()));
        this.setLayout(new BorderLayout());
        this.add(new JLabel(INFO_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));

        final JPanel playerPanel;
        final JPanel objectivePanel;

        playerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        final int n1 = 2;
        final int n2 = 5;
        cnst.insets = new Insets(n1, n2, n1, n2);
        cnst.gridy = 0;
        this.pLabel = new JLabel();
        this.cLabel = new JLabel();
        cnst.gridy++;
        playerPanel.add(this.pLabel, cnst);
        cnst.gridy++;
        playerPanel.add(this.cLabel, cnst);
        playerPanel.setBackground(Color.WHITE);
        this.add(playerPanel, BorderLayout.CENTER);

        objectivePanel = new JPanel(new BorderLayout());
        final JLabel oLabel;
        oLabel = new JLabel(OBJECTIVE_TITLE);
        this.objText = new JTextArea();
        this.objText.setLineWrap(true);
        this.objText.setWrapStyleWord(true);
        this.objText.setEditable(false);
        objectivePanel.add(oLabel, BorderLayout.NORTH);
        objectivePanel.add(this.objText, BorderLayout.CENTER);

        this.add(objectivePanel, BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        this.pLabel
                .setText(new StringBuilder("Player ").append(Integer.toString(this.getCurrentPlayerId())).toString());
        this.cLabel.setText(new StringBuilder("Color : ").append(this.getCurrentPlayerColor()).toString());
        this.objText.setText(this.getCurrentPlayerObjective());
    }

    /**
     * Retrieves the current player id.
     * 
     * @return the current player id
     */
    private int getCurrentPlayerId() {
        return this.controller.getCurrentPlayer().getId();
    }

    /**
     * Retrieves the current player color.
     * 
     * @return the current player color
     */
    private String getCurrentPlayerColor() {
        return this.controller.getCurrentPlayer().getColorPlayer().getName();
    }

    /**
     * Retrieves the current player objective.
     * 
     * @return the current player objective
     */
    private String getCurrentPlayerObjective() {
        return this.controller.getCurrentPlayer().getObjective().getDescription();
    }
}
