package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.SideZone;

/**
 * Implemetation of {@link GameZone} interface.
 * Models the zone {@code JPanel} that contains the board
 * and the sidebar.
 */
public final class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instace of {@link GameZone} interface to interface with all
     * thew views in the {@code GamePanel}.
     * 
     * @param board the game board view
     * @param side  the sidebar
     */
    public GamePanel(final BoardZone board, final SideZone side) {
        this.setLayout(new BorderLayout());
        this.add((JPanel) board, BorderLayout.CENTER);
        this.add((JPanel) side, BorderLayout.EAST);
    }
}
