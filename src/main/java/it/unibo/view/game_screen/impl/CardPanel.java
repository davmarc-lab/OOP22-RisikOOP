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

import it.unibo.view.game_screen.api.CardZone;

public class CardPanel extends JPanel implements CardZone {

    private static final double HEIGHT_SCALING = 0.3;
    private static final String CARD_LABEL = "CARD PANEL";
    private static final int BORDER_SIZE = 4;

    public CardPanel(final Dimension size) {
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Double.valueOf(size.getWidth()).intValue(),
                Double.valueOf(size.getHeight() * HEIGHT_SCALING).intValue()));
        this.add(new JLabel(CARD_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));

        final JPanel cardsPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.ipady = (int) (this.getPreferredSize().getHeight() / 10);
        cnst.ipadx = 10;
        cnst.insets = new Insets(2, 2, 2, 2);

        final JButton firstCard = new JButton("Infantry");
        final JButton secondCard = new JButton("Cavalry");
        final JButton thridCard = new JButton("Artillery");
        
        final JLabel firstText = new JLabel("1");
        cardsPanel.add(firstText, cnst);
        cardsPanel.add(firstCard, cnst);
        cnst.gridy++;
        final JLabel secondText = new JLabel("1");
        cardsPanel.add(secondText, cnst);
        cardsPanel.add(secondCard, cnst);
        cnst.gridy++;
        final JLabel thirdText = new JLabel("1");
        cardsPanel.add(thirdText, cnst);
        cardsPanel.add(thridCard, cnst);
        cnst.gridy++;

        final JButton playCards = new JButton("Play");
        JPanel play = new JPanel(new FlowLayout());
        play.add( playCards);

        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(play, BorderLayout.SOUTH);
    }
}
