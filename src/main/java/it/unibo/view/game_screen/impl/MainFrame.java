package it.unibo.view.game_screen.impl;

import javax.swing.JFrame;

import it.unibo.common.Constants;
import it.unibo.start.Engine;
import it.unibo.view.game_screen.MainPanel;
import it.unibo.view.game_screen.api.MainView;

/**
 * This class extends JFrame and defines the main frame of the game.
 */
public class MainFrame extends JFrame implements MainView {

    private final Engine engine;

    /**
     * Creates the main frame.
     * 
     * @param engine the engine of the game
     */
    public MainFrame(final Engine engine) {
        super(Constants.FRAME_NAME);
        this.engine = engine;
        this.getContentPane().add(new MainPanel(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
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

}
