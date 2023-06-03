package it.unibo.view.game_screen.impl;

import javax.swing.JFrame;

import it.unibo.start.Engine;
import it.unibo.view.game_screen.MainPanel;
import it.unibo.view.game_screen.api.MainView;

/**
 * This class extends JFrame and defines the main frame of the game.
 */
public class MainFrame extends JFrame implements MainView {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "RisikOOP";

    private final transient Engine engine;

    /**
     * Creates the main frame.
     * 
     * @param engine the engine of the game
     */
    public MainFrame(final Engine engine) {
        super(FRAME_NAME);
        this.engine = engine;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        engine.createGameLoop();
        engine.createGUI();
        engine.startEngine();
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
        this.setVisible(true);
    }
}
