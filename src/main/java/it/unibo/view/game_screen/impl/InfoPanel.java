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

import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

public class InfoPanel extends JPanel implements InfoZone {

    private static final String INFO_LABEL = "PLAYER INFO";
    private static final String OBJECTIVE_TITLE = "OBJECTIVE: ";
    private static final int BORDER_SIZE = 4;

    private final SideZone parent;
    private final JPanel playerPanel;
    private final JPanel objectivePanel;
    private JLabel pLabel;
    private JLabel cLabel;
    private final JLabel oLabel;
    private JTextArea objText;

    public InfoPanel(final Dimension size, final SideZone parent) {
        super();
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.add(new JLabel(INFO_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));

        this.playerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.insets = new Insets(2, 5, 2, 5);
        cnst.gridy = 0;
        this.pLabel = new JLabel();
        this.cLabel = new JLabel();
        cnst.gridy++;
        this.playerPanel.add(pLabel, cnst);
        cnst.gridy++;
        this.playerPanel.add(cLabel, cnst);
        this.add(playerPanel, BorderLayout.CENTER);

        this.objectivePanel = new JPanel(new BorderLayout());
        this.oLabel = new JLabel(OBJECTIVE_TITLE);
        this.objText = new JTextArea();
        this.objText.setLineWrap(true);
        this.objText.setWrapStyleWord(true);
        this.objText.setEditable(false);
        this.objectivePanel.add(this.oLabel, BorderLayout.NORTH);
        this.objectivePanel.add(this.objText, BorderLayout.CENTER);

        this.add(this.objectivePanel, BorderLayout.SOUTH);
        this.updateUI();
    }

    @Override
    public SideZone getParentEntity() {
        return this.parent;
    }

    private int getCurrentPlayerId() {
        return this.parent.getParentEntity().getController().getGameLoop().getBoard().getCurrentPlayer().getId();
    }

    private String getCurrentPlayerColor() {
        return this.parent.getParentEntity().getController().getGameLoop().getBoard().getCurrentPlayer()
                .getColorPlayer().getName();
    }

    private String getCurrentPlayerObjective() {
        return this.parent.getParentEntity().getController().getGameLoop().getBoard().getCurrentPlayer().getObjective()
                .getDescription();
    }

    @Override
    public void updateView() {
        this.pLabel
                .setText(new StringBuilder("Player ").append(Integer.toString(this.getCurrentPlayerId())).toString());
        this.cLabel.setText(new StringBuilder("Color : ").append(this.getCurrentPlayerColor()).toString());
        this.objText.setText(this.getCurrentPlayerObjective());
    }
}
