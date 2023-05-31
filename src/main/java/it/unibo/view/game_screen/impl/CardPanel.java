package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.controller.playerhand.api.PlayerHandController;
import it.unibo.view.game_screen.api.CardZone;

public class CardPanel extends JPanel implements CardZone {

    private static final double HEIGHT_SCALING = 0.3;
    private static final String CARD_LABEL = "PLAYER HAND";
    private static final int BORDER_SIZE = 4;

    private final JLabel firstText = new JLabel("NaN");
    private final JLabel secondText = new JLabel("NaN");
    private final JLabel thirdText = new JLabel("NaN");

    private final JLabel firstPlayed = new JLabel("NaN");
    private final JLabel secondPlayed = new JLabel("NaN");
    private final JLabel thirdPlayed = new JLabel("NaN");

    private PlayerHandController phc;

    public CardPanel(final Dimension size, final PlayerHandController phc) {
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Double.valueOf(size.getWidth()).intValue(),
                Double.valueOf(size.getHeight() * HEIGHT_SCALING).intValue()));
        this.add(new JLabel(CARD_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));

        final JPanel cardsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        var n = (int) (this.getPreferredSize().getHeight() * 0.05);
        cnst.insets = new Insets(2, 2, 2, 2);

        final JButton firstCard = new JButton("Infantry");
        firstCard.setMargin(new Insets(n, n, n, n));
        final JButton secondCard = new JButton("Cavalry");
        secondCard.setMargin(new Insets(n, n, n, n));
        final JButton thridCard = new JButton("Artillery");
        thridCard.setMargin(new Insets(n, n, n, n));
        
        cardsPanel.add(this.firstText, cnst);
        cardsPanel.add(firstCard, cnst);
        cardsPanel.add(this.firstPlayed, cnst);
        cnst.gridy++;
        
        cardsPanel.add(this.secondText, cnst);
        cardsPanel.add(secondCard, cnst);
        cardsPanel.add(this.secondPlayed, cnst);
        cnst.gridy++;
        cardsPanel.add(this.thirdText, cnst);
        cardsPanel.add(thridCard, cnst);
        cardsPanel.add(this.thirdPlayed, cnst);
        cnst.gridy++;

        final JButton playCards = new JButton("Play");
        final JButton resetCards = new JButton("Reset");

        firstCard.addActionListener(e -> {
            this.phc.addInputCard(this.phc.getArmyTypeFromString(firstCard.getText()));
            this.firstPlayed.setText(String.valueOf(Integer.parseInt(this.firstPlayed.getText()) + 1));
            this.updateView();
        });

        secondCard.addActionListener(e -> {
            this.phc.addInputCard(this.phc.getArmyTypeFromString(secondCard.getText()));
            this.secondPlayed.setText(String.valueOf(Integer.parseInt(this.secondPlayed.getText()) + 1));
            this.updateView();
        });

        thridCard.addActionListener(e -> {
            this.phc.addInputCard(this.phc.getArmyTypeFromString(thridCard.getText()));
            this.thirdPlayed.setText(String.valueOf(Integer.parseInt(this.thirdPlayed.getText()) + 1));
            this.updateView();
        });

        resetCards.addActionListener(e -> {
            this.resetUserInput();
        });

        final JPanel playPanel = new JPanel(new FlowLayout());
        playPanel.add(playCards);
        playPanel.add(resetCards);

        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(playPanel, BorderLayout.SOUTH);
    }

    private void resetUserInput() {
        this.firstPlayed.setText("0");
        this.secondPlayed.setText("0");
        this.thirdPlayed.setText("0");
    }

    private int getFirstCardCount() {
        return this.phc == null ? -1 : this.phc.getPlayerFirstCards();
    }

    private int getSecondCardCount() {
        return this.phc == null ? -1 : this.phc.getPlayerSecondCards();
    }

    private int getThirdCardCount() {
        return this.phc == null ? -1 : this.phc.getPlayerThirdCards();
    }

    @Override
    public void setController(PlayerHandController controller) {
        this.phc = controller;
    }

    @Override
    public void updateView() {
        this.firstText.setText(String.valueOf(this.getFirstCardCount()));
        this.secondText.setText(String.valueOf(this.getSecondCardCount()));
        this.thirdText.setText(String.valueOf(this.getThirdCardCount()));
    }
}
