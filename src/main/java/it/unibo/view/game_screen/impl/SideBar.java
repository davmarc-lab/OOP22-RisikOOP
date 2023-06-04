package it.unibo.view.game_screen.impl;

import javax.swing.JPanel;

import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * Implementation of SideZone interface.
 */
public class SideBar extends JPanel implements SideZone {

    private static final long serialVersionUID = 1L;

    private static final double WIDTH_SCALING = 0.08;
    private static final int VGAP = 2;

    private final InfoZone ip;
    private final CardZone cp;
    private final ButtonZone bp;

    /**
     * Creates the side bar.
     * 
     * @param size   the size of the board zone
     * @param parent the parent entity
     */
    public SideBar(final Dimension size, final InfoZone info, final CardZone card, final ButtonZone button) {
        super();
        final Dimension dim = new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue());
        this.setPreferredSize(dim);
        this.setBackground(Color.WHITE);
        final BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = info;
        this.cp = card;
        this.bp = button;
        this.add((InfoPanel) this.ip, BorderLayout.NORTH);
        this.add((CardPanel) this.cp, BorderLayout.CENTER);
        this.add((ButtonPanel) this.bp, BorderLayout.SOUTH);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public ButtonZone getButtonPanel() {
        return this.bp;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CardZone getCardPanel() {
        return this.cp;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public InfoZone getInfoPanel() {
        return this.ip;
    }
}
