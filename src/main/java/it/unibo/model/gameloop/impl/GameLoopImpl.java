package it.unibo.model.gameloop.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.controller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.board.impl.GameBoardImpl;
import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.gameloop.api.PhaseManager;
import it.unibo.model.gameloop.api.PhaseManager.Phase;
import it.unibo.model.territory.api.Territory;

/**
 * This class is used to process the input received from the view
 * and tell the view what to render.
 */
public class GameLoopImpl implements GameLoop {

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
    private boolean prepare = true;

    private int troopsCounter = 0;

    /**
     * Prepares all the necessary components.
     * 
     * @param controller controller
     */
    public GameLoopImpl(final MainController controller) {
        this.controller = controller;
        this.board = new GameBoardImpl();
        this.phaseManager = new PhaseManagerImpl();
    }

    @Override
    public void start() {
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
    private void prepareGame(final Territory t) {
        if (this.troopsCounter < PREPARATION_TROOPS) {
            this.troopsCounter++;
            this.board.getCurrentPlayer().addTroops(-1);
            t.addTroops(1);
        }
        if (this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0).count() == Constants.MAX_PLAYERS) {
            this.phaseManager.switchToNextPhase();
            this.board.getTurnManager().switchToNextPlayer();
            this.prepare = false;
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

    @Override
    public void processInput(final Object input) {
        Territory t;
        if (input instanceof String) {
            t = this.board.getGameTerritories().getTerritory((String) input);
        } else {
            // t = card
            t = this.board.getGameTerritories().getTerritory((String) input);
        }
        if (this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0).count() != Constants.MAX_PLAYERS
            && this.prepare) {
            this.prepareGame(t);
            return;
        }
        switch (this.phaseManager.getCurrentPhase()) {
            case PREPARATION:
                if (this.board.getCurrentPlayer().getTroops() > 0) {
                    t.addTroops(1);
                    this.board.getCurrentPlayer().addTroops(-1);
                    if (this.board.getCurrentPlayer().getTroops() == 0) {
                        this.phaseManager.switchToNextPhase();
                        this.controller.sendMessage("You can now play your cards.");
                    }
                }
                break;
            case PLAY_CARDS:
                break;
            case COMBAT:
                if (this.first.isEmpty()) {
                    this.first = Optional.of(t);
                    this.setAvailableTerritories(this.first.get().getAdjTerritories().stream()
                            .filter(terr -> !this.board.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(COMBAT_MESSAGE);
                } else {
                    this.second = Optional.of(t);
                    this.board.instanceCombat(new Pair<>(this.board.getCurrentPlayer(), this.first.get()),
                            new Pair<>(this.board.getAllPlayers().stream()
                                    .filter(p -> p.getTerritories().contains(this.second.get()))
                                    .findAny().get(),
                                    this.second.get()));
                    this.controller.sendMessage(RESET_COMBAT_MESSAGE);
                    this.resetClicks();
                }
                break;
            case MOVEMENT:
                if (this.first.isEmpty()) {
                    this.first = Optional.of(t);
                    this.setAvailableTerritories(this.first.get().getAdjTerritories().stream()
                            .filter(terr -> this.board.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(MOVEMENT_MESSAGE);
                } else {
                    this.second = Optional.of(t);
                    this.board.instanceMovement(this.first.get(), this.second.get());
                    this.first = Optional.empty();
                    this.second = Optional.empty();
                    this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                    this.controller.sendMessage(RESET_MOVEMENT_MESSAGE);
                    this.resetClicks();
                }
                break;
            case END_TURN:
                break;
            default:
                break;
        }
    }

    @Override
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

    @Override
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

    @Override
    public void endPlayerTurn() {
        this.phaseManager.switchToPhase(Phase.PREPARATION);
        this.board.getTurnManager().switchToNextPlayer();
        this.board.defineBonusArmies();
        this.controller.sendMessage("It's Player" + this.board.getCurrentPlayer().getId()
                + "'s turn.\nYou can now assign your bonus troops to your territories.");
        this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
    }

    @Override
    public void setAvailableTerritories(final Set<Territory> territories) {
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

    @Override
    public PhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    @Override
    public GameBoard getBoard() {
        return this.board;
    }

    @Override
    public MainController getController() {
        return this.controller;
    }

}
