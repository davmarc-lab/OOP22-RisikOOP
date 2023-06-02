package it.unibo.start;

import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.impl.MainControllerImpl;
import it.unibo.model.gameloop.impl.GameLoopImpl;
import it.unibo.view.game_screen.impl.GamePanel;
import it.unibo.view.game_screen.impl.MainFrame;

/**
 * This class is used to start the application.
 */
public final class Engine {

    private MainController controller;
    private MainFrame frame;

    /**
     * Sets up the controller.
     */
    public Engine() {
        this.controller = new MainControllerImpl(this);
    }

    /**
     * Creates the game frame.
     */
    public void startApp() {
        this.frame = new MainFrame(this);
    }

    /**
     * Creates a new instance of MainController.
     */
    public void createController() {
        this.controller = new MainControllerImpl(this);
    }

    /**
     * Creates an instance of the game loop that will be used during the game inside the controller.
     */
    public void createGameLoop() {
        this.controller.setGameLoop(new GameLoopImpl(this.controller));
    }

    /**
     * Creates an instance of the gui that will be used during the game inside the controller.
     */
    public void createGUI() {
        this.controller.setGameZone(new GamePanel(this.controller));
    }

    /**
     * Starts the game.
     */
    public void startEngine() {
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().revalidate();
        this.frame.getContentPane().repaint();
        this.frame.getContentPane().add((JPanel) this.controller.getGameZone());
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.controller.startLoop();
    }

    /**
     * main.
     * @param args
     */
    public static void main(final String[] args) {
        new Engine().startApp();
    }

}
