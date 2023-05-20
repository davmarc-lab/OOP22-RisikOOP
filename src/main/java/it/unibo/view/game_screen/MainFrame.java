package it.unibo.view.game_screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final String FRAME_NAME = "RisikOOP";

    private final JPanel panel;

    public MainFrame() {
        super(FRAME_NAME);
        this.panel = new MainPanel();
        this.getContentPane().add(this.panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
