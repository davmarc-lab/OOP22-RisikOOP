package it.unibo.view.game_screen.impl;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.view.game_screen.api.CardZone;

public class CardPanel extends JPanel implements CardZone {

    public CardPanel(Dimension size) {
        super();
        this.setPreferredSize(new Dimension((int) (size.getWidth()), (int) (size.getHeight()  * 0.3)));
        this.add(new JLabel("CARDPANEL"));
    }

}
