package it.unibo.view.game_screen;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import it.unibo.common.Constants;

public class MainFrame extends JFrame {

    public MainFrame() {
        super(Constants.FRAME_NAME);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new MainPanel(this), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
