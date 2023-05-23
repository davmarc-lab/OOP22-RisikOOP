package it.unibo.view.movement;

import javax.swing.JFrame;

import it.unibo.controller.popup.PlayerPopupController;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.impl.TerritoryImpl;

import java.awt.BorderLayout;

public class MovementFrame extends JFrame {
    
    private static final String FRAME_NAME = "Displacements";

    public MovementFrame(final PlayerPopupController ppc) {
        super(new String(new StringBuilder(FRAME_NAME).append(" Player ").append(String.valueOf(ppc.getPlayer().getId()))));
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new MovementPanel(this, ppc), BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        var t = new TerritoryImpl("Cina");
        t.addTroops(2);
        new MovementFrame(new PlayerPopupController(new PlayerImpl(1), t));
    }

}
