package it.unibo.controller.gamecontroller.impl;

import java.util.Set;

import javax.swing.JOptionPane;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.controller.playerhand.impl.PlayerHandControllerImpl;
import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.gameloop.impl.GameLoopImpl;
import it.unibo.model.player.api.Player;
import it.unibo.view.game_screen.api.BoardZone;
import it.unibo.view.game_screen.api.ButtonZone;
import it.unibo.view.game_screen.api.CardZone;
import it.unibo.view.game_screen.api.GameZone;
import it.unibo.view.game_screen.api.InfoZone;
import it.unibo.view.game_screen.impl.BoardPanel;
import it.unibo.view.game_screen.impl.ButtonPanel;
import it.unibo.view.game_screen.impl.CardPanel;
import it.unibo.view.game_screen.impl.GamePanel;
import it.unibo.view.game_screen.impl.InfoPanel;

/**
 * Implementation of MainController.
 */
public class MainControllerImpl implements MainController {

    private final StartController startController;
    private final GameLoop loop;
    private final BoardZone board;
    private final InfoZone info;
    private final CardZone card;
    private final ButtonZone button;

    /**
     * Basic constructor that links the controller to the engine.
     * 
     * @param startController the start controller of the application
     */
    public MainControllerImpl(final StartController startController) {
        this.startController = startController;
        this.loop = new GameLoopImpl(this);
        this.board = new BoardPanel(this);
        this.info = new InfoPanel(this.board.getDimension(), this);
        this.card = new CardPanel(this.board.getDimension(), this);
        this.button = new ButtonPanel(this.board.getDimension(), this);
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
        return new GameLoopImpl(loop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameZone getGameZone() {
        return new GamePanel(this.board, this.info, this.card, this.button);
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
        this.info.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCards() {
        this.card.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardController() {
        this.card.setController(new PlayerHandControllerImpl(this.getCurrentPlayer(), this.card));
    }

}
