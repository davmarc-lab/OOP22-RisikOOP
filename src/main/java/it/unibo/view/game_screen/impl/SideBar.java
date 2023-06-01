package it.unibo.view.game_screen.impl;

import javax.swing.JPanel;

import it.unibo.controller.playerhand.impl.PlayerHandControllerImpl;
import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * Implementation of SideZone interface.
 */
public class SideBar extends JPanel implements SideZone {

    private static final double WIDTH_SCALING = 0.08;
    private static final double INFO_PANEL_HEIGHT_SCALING = 0.45;
    private static final int VGAP = 2;

    private final InfoZone ip;
    private final CardZone cp;
    private final ButtonZone bp;
    private final GameZone parent;

    /**
     * Creates the side bar.
     * 
     * @param size the size of the board zone
     * @param parent the parent entity
     */
    public SideBar(final Dimension size, final GameZone parent) {
        super();
        this.parent = parent;
        this.setPreferredSize(new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue()));
        this.setBackground(Color.WHITE);
        final BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = new InfoPanel(this);
        ((InfoPanel) this.ip)
                .setPreferredSize(new Dimension(Double.valueOf(this.getPreferredSize().getWidth()).intValue(),
                        Double.valueOf(this.getPreferredSize().getHeight() * INFO_PANEL_HEIGHT_SCALING).intValue()));
        this.add((InfoPanel) ip, BorderLayout.NORTH);
        this.cp = new CardPanel(this.getPreferredSize(),
                new PlayerHandControllerImpl(parent.getController().getGameLoop().getBoard().getCurrentPlayer()));
        this.add((CardPanel) cp, BorderLayout.CENTER);
        this.bp = new ButtonPanel(this.getPreferredSize());
        this.add((ButtonPanel) bp, BorderLayout.SOUTH);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public GameZone getParentEntity() {
        return this.parent;
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
