package it.unibo.view.movement;

import javax.swing.JOptionPane;

import it.unibo.controller.popup.PlayerPopupController;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.impl.PlayerBuilderImpl;
import it.unibo.model.territory.impl.TerritoryImpl;

public class MovementPane extends JOptionPane {

    public MovementPane(final PlayerPopupController ppc) {
        super();
        JOptionPane.showConfirmDialog(null, new MovementPanel(ppc),
                new StringBuilder("Discplacements Player ").append(ppc.getPlayer().getId()).toString(),
                JOptionPane.OK_CANCEL_OPTION);
    }

    public static void main(String[] args) {
        new MovementPane(new PlayerPopupController(
                PlayerBuilderImpl.newBuilder().id(1).objective(new ObjectiveImpl()).build(),
                new TerritoryImpl("Italy")));
    }

}
