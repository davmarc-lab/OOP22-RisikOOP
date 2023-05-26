package it.unibo.controller.impl;

import java.util.Set;

import javax.swing.JOptionPane;

import it.unibo.controller.api.MainController;
import it.unibo.model.gameloop.GameLoop;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.game_screen.BoardPanel;

/**
 * Implementation of MainController.
 */
public final class MainControllerImpl implements MainController {

    private final BoardPanel gui;
    private final GameLoop loop;

    /**
     * Basic constructor generated from a GUI, creates a GameLoop.
     * @param gui the GUI that generated this controller
     */
    public MainControllerImpl(final BoardPanel gui) {
        this.gui = gui;
        this.loop = new GameLoop(this);
    }

    @Override
    public void sendInput(final Object input) {
        this.loop.processInput(input);
    }

    @Override
    public void sendMessage(final String message) {
        JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void disableTerritories(final Set<Territory> territories) {
        this.gui.disableButtons(territories);
    }

    @Override
    public void enableAllTerritories() {
        this.gui.enableAll();
    }

    @Override
    public void disableAllTerritories() {
        this.gui.disableAll();
    }

    @Override
    public void switchToCombat() {
        this.loop.startCombat();
    }

    @Override
    public void switchToMovement() {
        this.loop.startMovement();
    }

    @Override
    public void endTurn() {
        this.loop.endPlayerTurn();
    }

}
