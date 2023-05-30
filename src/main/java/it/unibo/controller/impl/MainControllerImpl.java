package it.unibo.controller.impl;

import java.util.Set;

import javax.swing.JOptionPane;

import it.unibo.controller.api.MainController;
import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.game_screen.api.GameZone;

/**
 * Implementation of MainController.
 */
public final class MainControllerImpl implements MainController {

    private GameZone gui;
    private GameLoop loop;

    /**
     * Empty constructor.
     */
    public MainControllerImpl() {
    }

    @Override
    public void setGameLoop(final GameLoop loop) {
        this.loop = loop;
    }

    @Override
    public void setGameZone(final GameZone gui) {
        this.gui = gui;
    }

    @Override
    public void startLoop() {
        this.loop.start();
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
        this.gui.getBoard().disableButtons(territories);
    }

    @Override
    public void enableAllTerritories() {
        this.gui.getBoard().enableAll();
    }

    @Override
    public void disableAllTerritories() {
        this.gui.getBoard().disableAll();
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

    @Override
    public GameLoop getGameLoop() {
        return this.loop;
    }

    @Override
    public GameZone getGameZone() {
        return this.gui;
    }

}
