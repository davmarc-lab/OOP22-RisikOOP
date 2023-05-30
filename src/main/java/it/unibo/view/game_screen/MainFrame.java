package it.unibo.view.game_screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.common.Constants;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.impl.GamePanel;

/**
 * This class extends JFrame and defines the main frame of the game.
 */
public class MainFrame extends JFrame {

    /**
     * Creates the main frame.
     */
    public MainFrame() {
        super(Constants.FRAME_NAME);
        this.getContentPane().add(new MainPanel(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void startGame() {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        GameZone game = new GamePanel();
        this.getContentPane().add((JPanel) game);
        this.pack();
        this.setLocationRelativeTo(null);
        game.getController().startLoop();
    }

}
