package it.unibo.start;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import it.unibo.controller.gamecontroller.impl.StartControllerImpl;

/**
 * This utility class starts the application.
 */
public final class GameStart {

    /**
     * This is used to make this class an utility class.
     * 
     * @throws UnsupportedOperationException
     */
    private GameStart() {
        Logger.getLogger(GameStart.class.getName()).log(Level.WARNING, "Called the constructor of an utility class.");
        throw new UnsupportedOperationException("Cannot create an object of this class, this is an utility class");
    }

    /**
     * Main method of the RisikOOP that starts the application.
     * 
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> new StartControllerImpl().startView());
    }
}
