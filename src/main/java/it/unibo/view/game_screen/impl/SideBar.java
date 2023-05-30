package it.unibo.view.game_screen.impl;

import javax.swing.JPanel;

import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class SideBar extends JPanel implements SideZone {

    private static final double WIDTH_SCALING = 0.08;
    private static final int VGAP = 2;

    private final InfoZone ip;
    private final CardZone cp;
    private final ButtonZone bp;

    public SideBar(final Dimension size) {
        super();
        this.setPreferredSize(new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue()));
        this.setBackground(Color.WHITE);
        BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = new InfoPanel(this.getPreferredSize());
        ((InfoPanel) this.ip).setPreferredSize(new Dimension(Double.valueOf(this.getPreferredSize().getWidth()).intValue(),
                Double.valueOf(this.getPreferredSize().getHeight() * 0.45).intValue()));
        this.add((InfoPanel) ip, BorderLayout.NORTH);
        this.cp = new CardPanel(this.getPreferredSize());
        this.add((CardPanel) cp, BorderLayout.CENTER);
        this.bp = new ButtonPanel(this.getPreferredSize());
        this.add((ButtonPanel) bp, BorderLayout.SOUTH);
    }

    @Override
    public ButtonZone getButtonPanel() {
        return this.bp;
    }

    @Override
    public CardZone getCardPanel() {
        return this.cp;
    }

    @Override
    public InfoZone getInfoPanel() {
        return this.ip;
    }
}
