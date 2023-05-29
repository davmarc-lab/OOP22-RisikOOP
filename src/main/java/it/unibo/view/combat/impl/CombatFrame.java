package it.unibo.view.combat.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.controller.combat.api.CombatController;
import it.unibo.view.combat.api.CombatView;

public class CombatFrame extends JFrame implements CombatView {

    private CombatController cc;

    public CombatFrame(CombatController cc) {
        this.cc = cc;
        cc.setFrame(this);
        this.setVisible(false);
    }

    @Override
    public void startPopup() {
        String[] options = { "Confirm", "Cancel" };
        int result = JOptionPane.showOptionDialog(
                null,
                new CombatPanel(cc),
                new StringBuilder("Player").append(cc.getFirstObject().getId()).append(" troops").toString(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, // icon
                options,
                options[1]);
        if (result == 0) {
            cc.setValue();
            this.dispose();
        } else {
            this.dispose();
        }
    }

    @Override
    public void setController(CombatController controller) {
        this.cc = controller;
    }
}
