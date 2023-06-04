package it.unibo.start;

import javax.swing.SwingUtilities;

import it.unibo.controller.gamecontroller.impl.StartControllerImpl;

public class GameStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartControllerImpl().startView());
    }
}
