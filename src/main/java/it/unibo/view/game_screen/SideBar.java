package it.unibo.view.game_screen;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class SideBar extends JPanel {

    private static final double WIDTH_SCALING = 0.08;
    
    public SideBar(final Dimension size) {
        super();
        this.setPreferredSize(new Dimension((int) (size.getWidth() * WIDTH_SCALING), (int) size.getHeight()));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.add(new ButtonPanel(this.getPreferredSize()), BorderLayout.SOUTH);
    }
}
