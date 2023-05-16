package it.unibo.view.game_screen;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The frame that contains all the necessary components to play Risiko.
 */
public class GameFrame extends JFrame {

    /**
     * Basic frame creation.
     */
    public GameFrame() {
        this.setLayout(new BorderLayout());
        //this.getContentPane().add(new BoardPanel(), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}
