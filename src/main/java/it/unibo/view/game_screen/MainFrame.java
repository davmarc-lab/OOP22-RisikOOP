package it.unibo.view.game_screen;

import javax.swing.JFrame;

import it.unibo.common.Constants;
import it.unibo.start.Engine;

/**
 * This class extends JFrame and defines the main frame of the game.
 */
public class MainFrame extends JFrame {

    private final Engine engine;

    /**
     * Creates the main frame.
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

    protected void startGame() {
        engine.createGameLoop();
        engine.createGUI();
        engine.startEngine();
    }

}
