package it.unibo.view.game_screen.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.view.game_screen.MainPanel;
import it.unibo.view.game_screen.api.MainView;

/**
 * This class extends JFrame and defines the main frame of the game.
 */
public class MainFrame extends JFrame implements MainView {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "RisikOOP";

    private final transient StartController controller;

    /**
     * Creates the main frame.
     * 
     * @param engine the engine of the game
     */
    public MainFrame(final StartController engine) {
        super(FRAME_NAME);
        this.controller = engine;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        controller.startGame();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void startMainMenu() {
        this.getContentPane().add(new MainPanel(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void changeToGamePanel() {
        this.changePanel((JPanel) this.controller.getMainController().getGameZone());
    }

    @Override
    public void closeView() {
        this.dispose();
    }

    @Override
    public void reveal() {
        this.changePanel(new MainPanel(this));
        this.setVisible(true);
    }

    private void changePanel(final JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}