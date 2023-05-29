package it.unibo.model.gameloop;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.controller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.board.impl.GameBoardImpl;
import it.unibo.model.gameloop.api.PhaseManager;
import it.unibo.model.gameloop.api.PhaseManager.Phase;
import it.unibo.model.gameloop.impl.PhaseManagerImpl;
import it.unibo.model.territory.api.Territory;

/**
 * This class is used to process the input received from the view
 * and tell the view what to render.
 */
public class GameLoop {

    private static final int PREPARATION_TROOPS = 3;
    private static final String COMBAT_MESSAGE = 
        "Select an adjacent enemy territory.\nIf you want to undo the attack and start another, press ATTACK";
    private static final String RESET_COMBAT_MESSAGE = 
        "You can attack again by selecting one of your territories.\nIf you don't want to attack press MOVE or END TURN";
    private static final String MOVEMENT_MESSAGE = 
        "Select an adjacent territory.\nIf you want to undo the movement and start another, press MOVE";
    private static final String RESET_MOVEMENT_MESSAGE = 
        "You can move again by selecting one of your territories.\nIf you don't want to move press END TURN";

    private final PhaseManager phaseManager;
    private final GameBoard board;
    private final MainController controller;

    private Set<Territory> disabledTerritories = new HashSet<>();
    private Optional<Territory> first;
    private Optional<Territory> second;

    private int troopsCounter = 0;

    /**
     * Prepares all the necessary components.
     * 
     * @param controller controller
     */
    public GameLoop(final MainController controller) {
        this.controller = controller;
        this.board = new GameBoardImpl();
        this.phaseManager = new PhaseManagerImpl();
        this.controller.sendMessage(
                "Game started, Player" + this.board.getCurrentPlayer().getId() + " start placing your troops");
        this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
        this.resetClicks();
    }

    /**
     * Players put 3 troops on their territories taking turns until all their troops
     * are placed.
     * 
     * @param input the selected territory
     */
    private void prepareGame(final Object input) {
        if (this.troopsCounter < PREPARATION_TROOPS && input instanceof String) {
            this.troopsCounter++;
            this.board.getCurrentPlayer().addTroops(-1);
            this.board.getGameTerritories().getTerritory((String) input).addTroops(1);
        }
        if (this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0)
                .count() == Constants.MAX_PLAYERS) {
            this.phaseManager.switchToNextPhase();
            this.board.getTurnManager().switchToNextPlayer();
            this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
            this.controller.sendMessage("Player" + this.board.getCurrentPlayer().getId()
                    + ", you can now play your cards to gain bonus troops");
        } else if (troopsCounter == PREPARATION_TROOPS) {
            this.troopsCounter = 0;
            this.board.getTurnManager().switchToNextPlayer();
            this.controller.sendMessage("Player" + this.board.getCurrentPlayer().getId()
                    + ", it's your turn to place 3 troops on your territories");
            this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
        }
    }

    /**
     * Checks the current phase of the turn and acts accordingly.
     * 
     * @param input the input received from the view, it can be a String (name of
     *              territory), a Card
     *              or an Integer (troops)
     */
    public void processInput(final Object input) {
        if (this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0).count() != Constants.MAX_PLAYERS) {
            this.prepareGame(input);
            return;
        }
        switch (this.phaseManager.getCurrentPhase()) {
            case PREPARATION:
                break;
            case PLAY_CARDS:
                break;
            case COMBAT:
                if (input instanceof String) {
                    if (this.first.isEmpty()) {
                        this.first = Optional.of(this.board.getGameTerritories().getTerritory((String) input));
                        this.setAvailableTerritories(this.first.get().getAdjTerritories().stream()
                                .filter(t -> !this.board.getCurrentPlayer().getTerritories().contains(t))
                                .collect(Collectors.toSet()));
                        this.controller.sendMessage(COMBAT_MESSAGE);
                    } else {
                        this.second = Optional.of(this.board.getGameTerritories().getTerritory((String) input));
                        this.board.instanceCombat(new Pair<>(this.board.getCurrentPlayer(), this.first.get()),
                                new Pair<>(this.board.getAllPlayers().stream()
                                        .filter(p -> p.getTerritories().contains(this.second.get()))
                                        .findAny().get(),
                                        this.second.get()));
                        this.controller.sendMessage(RESET_COMBAT_MESSAGE);
                        this.resetClicks();
                    }
                }
                break;
            case MOVEMENT:
                if (input instanceof String) {
                    if (this.first.isEmpty()) {
                        this.first = Optional.of(this.board.getGameTerritories().getTerritory((String) input));
                        this.setAvailableTerritories(this.first.get().getAdjTerritories().stream()
                                .filter(t -> this.board.getCurrentPlayer().getTerritories().contains(t))
                                .collect(Collectors.toSet()));
                        this.controller.sendMessage(MOVEMENT_MESSAGE);
                    } else {
                        this.second = Optional.of(this.board.getGameTerritories().getTerritory((String) input));
                        this.board.instanceMovement(this.first.get(), this.second.get());
                        this.first = Optional.empty();
                        this.second = Optional.empty();
                        this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                        this.controller.sendMessage(RESET_MOVEMENT_MESSAGE);
                        this.resetClicks();
                    }
                }
                break;
            case END_TURN:
                phaseManager.switchToNextPhase();
                board.getTurnManager().switchToNextPlayer();
                break;
            default:
                break;
        }
    }

    /**
     * Starts combat if the player is in play_cards phase.
     * If the phase is movement it will send a notification error.
     */
    public void startCombat() {
        switch (phaseManager.getCurrentPhase()) {
            case PLAY_CARDS, COMBAT:
                phaseManager.switchToPhase(Phase.COMBAT);
                controller.sendMessage("Select one of your territories");
                this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                this.resetClicks();
                break;
            case MOVEMENT:
                controller.sendMessage("Can't attack after switching to movement phase");
                break;
            default:
                break;
        }
    }

    /**
     * Starts movement if the player is in play_cards phase or in combat phase.
     */
    public void startMovement() {
        switch (this.phaseManager.getCurrentPhase()) {
            case PLAY_CARDS, COMBAT, MOVEMENT:
                phaseManager.switchToPhase(Phase.MOVEMENT);
                controller.sendMessage("Select one of your territories");
                this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                this.resetClicks();
                break;
            default:
                break;
        }
    }

    /**
     * Switches to END_TURN phase.
     */
    public void endPlayerTurn() {
        this.phaseManager.switchToPhase(Phase.END_TURN);
    }

    /**
     * Sets all the clickable buttons on the board and renders them.
     * 
     * @param territories the clickable territories
     */
    private void setAvailableTerritories(final Set<Territory> territories) {
        this.disabledTerritories.clear();
        this.disabledTerritories.addAll(this.board.getGameTerritories().getTerritories());
        this.disabledTerritories.removeAll(territories);
        this.controller.enableAllTerritories();
        this.controller.disableTerritories(disabledTerritories);
    }

    /**
     * Resets the previous 2 clicks.
     */
    private void resetClicks() {
        this.first = Optional.empty();
        this.second = Optional.empty();
    }

}
