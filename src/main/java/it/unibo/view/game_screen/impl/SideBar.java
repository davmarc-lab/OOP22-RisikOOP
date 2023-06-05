package it.unibo.view.game_screen.impl;

import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.playerhand.impl.PlayerHandControllerImpl;
import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;

/**
 * Implementation of {@link SideZone} interface, it provides methods to get all
 * the component of itself.
 */
public class SideBar extends JPanel implements SideZone, Cloneable {

    private static final long serialVersionUID = 1L;

    private static final double WIDTH_SCALING = 0.08;
    private static final int VGAP = 2;

    private final InfoZone ip;
    private final CardZone cp;
    private final ButtonZone bp;
    private final transient MainController controller;
    private final Dimension boardSize;

    /**
     * Constructs a {@code SideBar} containing the different panels.
     * 
     * @param size   the size of the sidebar
     * @param controller the main controller
     */
    public SideBar(final Dimension size, final MainController controller) {
        super();
        this.boardSize = new Dimension(Double.valueOf(size.getWidth()).intValue(), Double.valueOf(size.getHeight()).intValue());
        this.controller = controller;
        final Dimension dim = new Dimension(Double.valueOf(size.getWidth() * WIDTH_SCALING).intValue(),
                Double.valueOf(size.getHeight()).intValue());
        this.setPreferredSize(dim);
        this.setBackground(Color.WHITE);
        final BorderLayout layout = new BorderLayout();
        layout.setVgap(VGAP);
        this.setLayout(layout);
        this.ip = new InfoPanel(dim, this.controller);
        this.cp = new CardPanel(dim, this.controller);
        this.bp = new ButtonPanel(dim, this.controller);
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

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void updateInfo() {
        this.ip.updateView();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void updateCards() {
        this.cp.updateView();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setCardController() {
        this.cp.setController(new PlayerHandControllerImpl(this.controller.getCurrentPlayer(), cp));
    }

    @Override
    public Dimension getBoardSize() {
        return this.boardSize;
    }

    @Override
    public MainController getController() {
        return this.controller;
    }

    @Override
    public SideBar clone() throws CloneNotSupportedException {
        return (SideBar) super.clone();
    }

    @Override
    public SideZone getCopy() {
        try {
            return (SideZone) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(SideBar.class.getName()).log(Level.SEVERE, "Cannot create a copy of the object");
        }
        throw new IllegalCallerException("Cannot create a copy");
    }

}
