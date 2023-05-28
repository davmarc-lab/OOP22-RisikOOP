package it.unibo.view.game_screen;

import javax.swing.JPanel;

import it.unibo.view.game_screen.impl.CardPanel;
import it.unibo.view.game_screen.impl.InfoPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class SideBar extends JPanel {

    private static final double WIDTH_SCALING = 0.08;
    private static final int VGAP = 2;

    private final InfoPanel ip;
    private final CardPanel cp;
    private final ButtonPanel bp;

    public SideBar(final Dimension size) {
        super();
        this.setPreferredSize(new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue()));
        this.setBackground(Color.WHITE);
        BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = new InfoPanel(this.getPreferredSize());
        this.ip.setPreferredSize(new Dimension(Double.valueOf(this.getPreferredSize().getWidth()).intValue(),
                Double.valueOf(this.getPreferredSize().getHeight() * 0.45).intValue()));
        this.add(ip, BorderLayout.NORTH);
        this.cp = new CardPanel(this.getPreferredSize());
        this.add(cp, BorderLayout.CENTER);
        this.bp = new ButtonPanel(this.getPreferredSize());
        this.add(bp, BorderLayout.SOUTH);
    }

    public ButtonPanel getButtonPanel() {
        return this.bp;
    }
}
