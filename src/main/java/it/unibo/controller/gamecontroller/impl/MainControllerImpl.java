package it.unibo.controller.gamecontroller.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.gameengine.api.GameEngine;
import it.unibo.gameengine.impl.GameEngineImpl;
import it.unibo.model.player.api.Player;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.SideZone;
import it.unibo.view.game_screen.impl.BoardPanel;
import it.unibo.view.game_screen.impl.GamePanel;
import it.unibo.view.game_screen.impl.SideBar;

/**
 * Implementation of {@link MainController}.
 * It models the main controller that allows the {@link GameZone} to
 * communicate with the model.
 */
public class MainControllerImpl implements MainController, Cloneable {

    private final StartController startController;
    private final GameEngine engine;
    private final BoardZone board;
    private final SideZone side;

    /**
     * Basic constructor that links the controller to the engine.
     * 
     * @param startController the start controller of the application
     */
    public MainControllerImpl(final StartController startController) {
        this.startController = startController;
        this.engine = new GameEngineImpl();
        this.board = new BoardPanel(this);
        this.side = new SideBar(this.board.getDimension(), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startEngine() {
        this.engine.setController(this);
        this.engine.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendInput(final String input) {
        this.engine.processInput(input);
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
        this.board.disableButtons(territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableAllTerritories() {
        this.board.enableAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAllTerritories() {
        this.board.disableAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToCombat() {
        this.engine.startCombat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToMovement() {
        this.engine.startMovement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        this.engine.endPlayerTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getGameEngine() {
        return this.engine.getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameZone getGameZone() {
        return new GamePanel(this.board, this.side);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void randomize() {
        this.engine.randomize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerFromTerritory(final String territory) {
        return this.engine.getBoard().getAllPlayers().stream()
                .filter(p -> p.getTerritories()
                        .contains(this.engine.getBoard().getGameTerritories().getTerritory(territory)))
                .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.engine.getBoard().getPlayerFromId(this.engine.getTurnManager().getCurrentPlayerID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBonustTroops(final int troops) {
        this.getCurrentPlayer().addTroops(troops);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restartApp() {
        this.startController.closeView();
        this.startController.startView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateSquare(final String name) {
        this.board.updateTroopsView(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSquares() {
        this.board.setTroopsView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInfo() {
        this.side.updateInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCards() {
        this.side.updateCards();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardController() {
        this.side.setCardController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainControllerImpl clone() throws CloneNotSupportedException {
        return (MainControllerImpl) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainController getCopy() {
        try {
            return (MainController) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(MainControllerImpl.class.getName()).log(Level.SEVERE, "Cannot create the copy of the object.");
        }
        throw new IllegalCallerException("Cannot create a copy");
    }
}
