package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.SideZone;

/**
 * This class is used to contain the board and the side bar.
 */
public final class GamePanel extends JPanel implements GameZone {

    private static final long serialVersionUID = 1L;

    private final BoardZone board;
    private final SideZone side;
    private final transient MainController controller;

    /**
     * Sets up the controller and adds the board and the side bar.
     * 
     * @param controller
     */
    public GamePanel(final MainController controller) {
        this.controller = controller;
        this.board = new BoardPanel();
        this.side = new SideBar(((JPanel) this.board).getPreferredSize(), this);
        this.board.setController(this.controller);
        this.side.getButtonPanel().setController(this.controller);
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

    @Override
    public MainController getController() {
        return this.controller;
    }

}
