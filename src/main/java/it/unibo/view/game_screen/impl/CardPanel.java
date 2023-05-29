package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
    }

}
