package it.unibo.view.movement;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import it.unibo.controller.popup.PlayerPopupController;

public class MovementPanel extends JPanel {

    private static final double WIDTH_PERC = 0.3;
    private static final double HEIGHT_PERC = 0.4;

    private final Dimension dimension;
    private final PlayerPopupController ppc;

    public MovementPanel(final MovementFrame movementFrame, final PlayerPopupController ppc) {
        this.ppc = ppc;
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(
                new Dimension((int) (dimension.getWidth() * WIDTH_PERC),
                (int) (dimension.getHeight() * HEIGHT_PERC)));
    }
}
