package it.unibo.controller.gamecontroller.impl;

import java.util.Set;

import javax.swing.JOptionPane;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.player.api.Player;
import it.unibo.start.Engine;
import it.unibo.view.game_screen.api.GameZone;

/**
 * Implementation of MainController.
 */
public class MainControllerImpl implements MainController {

    private final Engine engine;
    private GameZone gui;
    private GameLoop loop;

    /**
     * Basic constructor that links the controller to the engine.
     * @param engine the engine that starts the game
     */
    public MainControllerImpl(final Engine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameLoop(final GameLoop loop) {
        this.loop = loop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameZone(final GameZone gui) {
        this.gui = gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startLoop() {
        this.loop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendInput(final String input) {
        this.loop.processInput(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(final String message) {
        JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableTerritories(final Set<String> territories) {
        this.gui.getBoard().disableButtons(territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableAllTerritories() {
        this.gui.getBoard().enableAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAllTerritories() {
        this.gui.getBoard().disableAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToCombat() {
        this.loop.startCombat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToMovement() {
        this.loop.startMovement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        this.loop.endPlayerTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameLoop getGameLoop() {
        return this.loop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameZone getGameZone() {
        return this.gui;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void randomize() {
        this.loop.randomize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerFromTerritory(final String territory) {
        return this.loop.getBoard().getAllPlayers().stream()
                .filter(p -> p.getTerritories()
                        .contains(this.loop.getBoard().getGameTerritories().getTerritory(territory)))
                        .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.loop.getBoard().getPlayerFromId(this.loop.getTurnManager().getCurrentPlayerID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restartApp() {
        this.engine.stopEngine();
        this.engine.startApp();
    }

}
