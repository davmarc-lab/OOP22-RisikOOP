package it.unibo.view.game_screen.impl;

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
import it.unibo.view.game_screen.api.InfoZone;

/**
 * Implementation of InfoZone interface.
 */
public class InfoPanel extends JPanel implements InfoZone {

    private static final long serialVersionUID = 1L;

    private static final String INFO_LABEL = "PLAYER INFO";
    private static final String OBJECTIVE_TITLE = "OBJECTIVE: ";
    private static final int BORDER_SIZE = 4;
    private static final double INFO_PANEL_HEIGHT_SCALING = 0.3;

    private final transient MainController controller;
    private final JLabel pLabel;
    private final JLabel cLabel;
    private final JTextArea objText;

    /**
     * Creates the panel with the player's informations inside the sidebar.
     * 
     * @param parent the parent entity
     */
    public InfoPanel(final Dimension dim, final MainController controller) {
        super();
        this.controller = controller;
        this.setPreferredSize(new Dimension(Double.valueOf(dim.getWidth()).intValue(),
                Double.valueOf(dim.getHeight() * INFO_PANEL_HEIGHT_SCALING).intValue()));
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

    private int getCurrentPlayerId() {
        return this.controller.getCurrentPlayer().getId();
    }

    private String getCurrentPlayerColor() {
        return this.controller.getCurrentPlayer().getColorPlayer().getName();
    }

    private String getCurrentPlayerObjective() {
        return this.controller.getCurrentPlayer().getObjective().getDescription();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        this.pLabel
                .setText(new StringBuilder("Player ").append(Integer.toString(this.getCurrentPlayerId())).toString());
        this.cLabel.setText(new StringBuilder("Color : ").append(this.getCurrentPlayerColor()).toString());
        this.objText.setText(this.getCurrentPlayerObjective());
    }
}
