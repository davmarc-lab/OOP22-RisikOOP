package it.unibo.view.game_screen;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private static final String FRAME_NAME = "RisikOOP";

    public MainFrame() {
        super(FRAME_NAME);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new MainPanel(this), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
