package it.unibo.model.gameloop.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.controller.api.MainController;
import it.unibo.controller.playerhand.impl.PlayerHandControllerImpl;
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
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private final PhaseManager phaseManager;
    private final GameBoard board;
    private final MainController controller;
    private final List<Territory> selectedTerritories;
    private final Set<Territory> disabledTerritories;

    private boolean prepare = true;

    /**
     * Prepares all the necessary components.
     * 
     * @param controller controller
     */
    public GameLoopImpl(final MainController controller) {
        this.selectedTerritories = new ArrayList<>();
        this.disabledTerritories = new HashSet<>();
        this.controller = controller;
        this.board = new GameBoardImpl();
        this.phaseManager = new PhaseManagerImpl();
    }

    @Override
    public void start() {
        this.controller.sendMessage(
                "Game started, Player" + this.board.getCurrentPlayer().getId() + " start placing your troops");
        this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
        this.controller.getGameZone().getSideBar().getInfoPanel().updateView();
    }

    /**
     * Adds a territory to the list of selected territories.
     * Places the troops if there are [PREPARATION_TROOPS] territories in it.
     * 
     * @param t the selected territory
     */
    private void prepareTroops(final Territory t) {
        this.selectedTerritories.add(t);
        if (this.selectedTerritories.size() == PREPARATION_TROOPS) {
            this.board.placeTroops(selectedTerritories);
            this.board.getTurnManager().switchToNextPlayer();
            this.selectedTerritories.clear();
            this.controller.getGameZone().getSideBar().getInfoPanel().updateView();
            this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
            if (this.checkAllInitialTroops()) {
                this.prepare = false;
                this.phaseManager.switchToNextPhase();
                this.controller.sendMessage("Player" + this.board.getCurrentPlayer().getId()
                        + ", you can now play your cards to gain bonus troops");
            } else {
                this.controller.sendMessage("Player" + this.board.getCurrentPlayer().getId()
                        + ", it's your turn to place 3 troops on your territories");
                this.controller.getGameZone().getSideBar().getCardPanel().setController(new PlayerHandControllerImpl(this.board.getCurrentPlayer()));
                this.controller.getGameZone().getSideBar().getCardPanel().updateView();
            }
        }
    }

    /**
     * Checks if the players have placed all their initial troops.
     * 
     * @return true or false
     */
    private boolean checkAllInitialTroops() {
        return this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0).count() == Constants.MAX_PLAYERS;
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
        if (this.prepare) {
            this.prepareTroops(t);
            return;
        }
        switch (this.phaseManager.getCurrentPhase()) {
            case PREPARATION:
                this.selectedTerritories.add(t);
                if (this.selectedTerritories.size() == this.board.getCurrentPlayer().getTroops()) {
                    this.board.placeTroops(selectedTerritories);
                    this.selectedTerritories.clear();
                    this.phaseManager.switchToNextPhase();
                    this.controller.sendMessage("You can now play your cards to gain bonus troops");
                }
                break;
            case PLAY_CARDS:
                break;
            case COMBAT:
                this.selectedTerritories.add(t);
                if (this.selectedTerritories.size() == 1) {
                    this.setAvailableTerritories(t.getAdjTerritories().stream()
                            .filter(terr -> !this.board.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(COMBAT_MESSAGE);
                } else {
                    this.board.instanceCombat(
                            new Pair<>(this.board.getCurrentPlayer(), this.selectedTerritories.get(FIRST)),
                            new Pair<>(this.board.getAllPlayers().stream()
                                    .filter(p -> p.getTerritories().contains(this.selectedTerritories.get(SECOND)))
                                    .findAny().get(), this.selectedTerritories.get(SECOND)));
                    this.selectedTerritories.clear();
                    this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                    this.controller.sendMessage(RESET_COMBAT_MESSAGE);
                }
                break;
            case MOVEMENT:
                this.selectedTerritories.add(t);
                if (this.selectedTerritories.size() == 1) {
                    this.setAvailableTerritories(t.getAdjTerritories().stream()
                            .filter(terr -> this.board.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(MOVEMENT_MESSAGE);
                } else {
                    this.board.instanceMovement(this.selectedTerritories.get(FIRST),
                            this.selectedTerritories.get(SECOND));
                    this.selectedTerritories.clear();
                    this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
                    this.controller.sendMessage(RESET_MOVEMENT_MESSAGE);
                }
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
                this.selectedTerritories.clear();
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
                this.selectedTerritories.clear();
                break;
            default:
                break;
        }
    }

    @Override
    public void endPlayerTurn() {
        if (this.phaseManager.getCurrentPhase().equals(Phase.PREPARATION)) {
            this.controller.sendMessage("Can't end turn your turn during preparation");
        } else {
            this.phaseManager.switchToPhase(Phase.PREPARATION);
            this.board.getTurnManager().switchToNextPlayer();
            this.controller.getGameZone().getSideBar().getInfoPanel().updateView();
            this.board.defineBonusArmies();
            this.controller.sendMessage("It's Player" + this.board.getCurrentPlayer().getId()
                    + "'s turn.\nYou can now assign your bonus troops to your territories.");
            this.controller.getGameZone().getSideBar().getCardPanel().setController(new PlayerHandControllerImpl(this.board.getCurrentPlayer()));
            this.controller.getGameZone().getSideBar().getCardPanel().updateView();
            this.setAvailableTerritories(this.board.getCurrentPlayer().getTerritories());
        }
    }

    @Override
    public void setAvailableTerritories(final Set<Territory> territories) {
        this.disabledTerritories.clear();
        this.disabledTerritories.addAll(this.board.getGameTerritories().getTerritories());
        this.disabledTerritories.removeAll(territories);
        this.controller.enableAllTerritories();
        this.controller.disableTerritories(disabledTerritories);
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
