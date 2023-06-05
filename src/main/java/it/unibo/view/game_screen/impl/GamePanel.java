package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.SideZone;

/**
 * Implemetation of {@link GameZone} interface. This class provides methods to interact with the views in the game UI.
 */
public final class GamePanel extends JPanel implements GameZone {

    private static final long serialVersionUID = 1L;

    private final BoardZone board;
    private final SideZone side;

    /**
     * Constructs an instace of {@link GameZone} interface to interface with all
     * thew views in the {@code GamePanel}.
     * 
     * @param board the game board view
     * @param side the sidebar
     */
    public GamePanel(final BoardZone board, final SideZone side) {
        this.board = board.getCopy();
        this.side = side.getCopy();
        this.setLayout(new BorderLayout());
        this.add((JPanel) board, BorderLayout.CENTER);
        this.add((JPanel) side, BorderLayout.EAST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardZone getBoard() {
        return this.board.getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SideZone getSideBar() {
        return this.side.getCopy();
    }

}
