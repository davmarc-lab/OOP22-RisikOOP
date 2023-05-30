package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

public class InfoPanel extends JPanel implements InfoZone {

    private static final String INFO_LABEL = "PLAYER INFO";
    private static final int BORDER_SIZE = 4;

    public InfoPanel(final Dimension size, final SideZone parent) {
        super();
        this.setLayout(new BorderLayout());
        this.add(new JLabel(INFO_LABEL, SwingConstants.CENTER), BorderLayout.NORTH);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));
    }
}
