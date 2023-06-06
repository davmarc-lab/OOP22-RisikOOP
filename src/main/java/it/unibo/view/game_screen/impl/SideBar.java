package it.unibo.view.game_screen.impl;

import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * Implementation of {@link SideZone} interface
 * It provides methods to get all of its components.
 */
public class SideBar extends JPanel implements SideZone, Cloneable {

    private static final long serialVersionUID = 1L;

    private static final double WIDTH_SCALING = 0.08;
    private static final int VGAP = 2;

    /**
     * The panel containing the player's information.
     */
    private final InfoZone ip;

    /**
     * The panel containing the player's cards.
     */
    private final CardZone cp;

    /**
     * Constructs a {@code SideBar} containing the different panels.
     * 
     * @param size       the size of the sidebar
     * @param controller the main controller
     */
    public SideBar(final Dimension size, final MainController controller) {
        super();
        final Dimension dim = new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue());
        this.setPreferredSize(dim);
        this.setBackground(Color.WHITE);
        final BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = new InfoPanel(dim, controller);
        this.cp = new CardPanel(dim, controller);
        final ButtonZone bp = new ButtonPanel(dim, controller);
        this.add((InfoPanel) this.ip, BorderLayout.NORTH);
        this.add((CardPanel) this.cp, BorderLayout.CENTER);
        this.add((ButtonPanel) bp, BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInfo() {
        this.ip.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCards() {
        this.cp.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardController() {
        this.cp.setController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SideBar clone() throws CloneNotSupportedException {
        return (SideBar) super.clone();
    }
}
