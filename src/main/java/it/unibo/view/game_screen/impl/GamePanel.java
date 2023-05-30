package it.unibo.view.game_screen.impl;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.controller.api.MainController;
import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.SideZone;

public class GamePanel extends JPanel implements GameZone {

    private final BoardZone board;
    private final SideZone side;
    private final MainController controller;

    public GamePanel() {
        this.setLayout(new BorderLayout());
        this.board = new BoardPanel();
        this.side = new SideBar(((JPanel) this.board).getPreferredSize());
        this.controller = new MainControllerImpl(this);
        this.board.setController(this.controller);
        this.side.getButtonPanel().setController(this.controller);
        this.add(((JPanel) this.board), BorderLayout.CENTER);
        this.add(((JPanel) this.side), BorderLayout.EAST);
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
