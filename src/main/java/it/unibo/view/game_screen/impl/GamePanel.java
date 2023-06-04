package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.api.SideZone;

/**
 * This class is used to contain the board and the side bar.
 */
public final class GamePanel extends JPanel implements GameZone {

    private static final long serialVersionUID = 1L;

    private final BoardZone board;
    private final SideZone side;

    /**
     * Sets up the controller and adds the board and the side bar.
     * 
     * @param controller
     */
    public GamePanel(final BoardZone board, final InfoZone info, final CardZone card, final ButtonZone button) {
        this.board = board;
        this.side = new SideBar(((JPanel) this.board).getPreferredSize(), info, card, button);
        this.setLayout(new BorderLayout());
        this.add((JPanel) this.board, BorderLayout.CENTER);
        this.add((JPanel) this.side, BorderLayout.EAST);
    }

    @Override
    public BoardZone getBoard() {
        return this.board;
    }

    @Override
    public SideZone getSideBar() {
        return this.side;
    }

}
