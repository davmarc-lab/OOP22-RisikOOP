package it.unibo.model.gameloop.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.common.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.board.impl.GameBoardImpl;
import it.unibo.model.gameloop.api.GameLoop;
import it.unibo.model.gameloop.api.PhaseManager;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.gameloop.api.PhaseManager.Phase;
import it.unibo.model.gamestate.api.GameState;
import it.unibo.model.gamestate.impl.GameStateImpl;
import it.unibo.model.modelconstants.ModelConstants;
import it.unibo.model.territory.api.Territory;

/**
 * This class is used to process the input received from the view
 * and tell the view what to render.
 */
public class GameLoopImpl implements GameLoop {

    private static final int PREPARATION_TROOPS = 3;
    private static final String COMBAT_MESSAGE = new StringBuilder("Select an adjacent enemy territory.")
            .append("\nIf you want to undo your attack and start another, press ATTACK.").toString();
    private static final String RESET_COMBAT_MESSAGE = new StringBuilder(
            "You can attack again by selecting one of your territories.")
            .append("\nIf you don't want to attack press MOVE or END TURN").toString();
    private static final String MOVEMENT_MESSAGE = new StringBuilder("Select an adjacent territory.")
            .append("\nIf you want to undo the movement and start another, press MOVE").toString();
    private static final String RESET_MOVEMENT_MESSAGE = new StringBuilder(
            "You can move again by selecting one of your territories.")
            .append("\nIf you don't want to move press END TURN").toString();
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private final PhaseManager phaseManager;
    private final GameBoard board;
    private final TurnManager turnManager;
    private final MainController controller;
    private final GameState gameState;
    private final List<Territory> selectedTerritories;
    private final List<Territory> disabledTerritories;
    private final Random rand;

    private boolean prepare = true;

    /**
     * Constructs an instance of {@link GameLoop} providing the
     * {@code MainController} of the application.
     * 
     * @param controller main controller of application
     */
    public GameLoopImpl(final MainController controller) {
        this.selectedTerritories = new ArrayList<>();
        this.disabledTerritories = new ArrayList<>();
        this.controller = controller;
        this.board = new GameBoardImpl();
        this.turnManager = this.board.getTurnManager();
        this.phaseManager = new PhaseManagerImpl();
        this.gameState = new GameStateImpl(this.controller);
        this.rand = new Random();
    }

    /**
     * Constructs a copy of the {@link GameLoop}.
     * 
     * @param loop
     */
    public GameLoopImpl(final GameLoop loop) {
        this.selectedTerritories = loop.getSelectedTerritories();
        this.disabledTerritories = loop.getDisabledTerritories();
        this.controller = loop.getController();
        this.board = loop.getBoard();
        this.turnManager = loop.getTurnManager();
        this.phaseManager = loop.getPhaseManager();
        this.gameState = loop.getGameState();
        this.rand = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller.sendMessage(new StringBuilder("Game started, Player")
                .append(this.controller.getCurrentPlayer().getId()).append(" start placing your troops").toString());
        this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories());
        this.controller.setSquares();
        this.controller.updateInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void randomize() {
        final List<Territory> list = new ArrayList<>();
        for (final var p : this.board.getAllPlayers()) {
            list.addAll(p.getTerritories());
            final int troops = p.getTroops();
            for (int i = 0; i < troops; i++) {
                this.board.placeTroops(list.get(this.rand.nextInt(list.size())), 1);
            }
            list.clear();
        }
        this.prepare = false;
        this.selectedTerritories.clear();
        this.phaseManager.switchToNextPhase();
        this.controller.setCardController();
        this.controller.updateCards();
        this.controller.updateInfo();
        this.board.getGameTerritories().getTerritories()
                .forEach(t -> this.controller.updateSquare(t.getName()));
        this.controller.sendMessage(new StringBuilder("You can now play your cards to gain bonus troops").toString());
    }

    /**
     * Adds a territory to the list of selected territories.
     * Places the troops if there are [PREPARATION_TROOPS] territories in it.
     * 
     * @param t the selected territory
     */
    private void updatePreparation(final Territory t) {
        this.selectedTerritories.add(t);
        this.controller.updateSquare(t.getName());
        if (this.selectedTerritories.size() == PREPARATION_TROOPS) {
            this.turnManager.switchToNextPlayer();
            this.selectedTerritories.clear();
            this.controller.updateInfo();
            this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories());
            if (this.checkAllInitialTroops()) {
                this.prepare = false;
                this.phaseManager.switchToNextPhase();
                this.controller.sendMessage(new StringBuilder("Player ")
                        .append(this.controller.getCurrentPlayer().getId())
                        .append(", you can now play your cards to gain bonus troops").toString());
                this.controller.setCardController();
                this.controller.updateCards();
            } else {
                this.controller.sendMessage(new StringBuilder("Player ")
                        .append(this.controller.getCurrentPlayer().getId())
                        .append(", it's your turn to place 3 troops on your territories").toString());
            }
        }
    }

    /**
     * Checks if the players have placed all their initial troops.
     * 
     * @return true or false
     */
    private boolean checkAllInitialTroops() {
        return this.board.getAllPlayers().stream().filter(p -> p.getTroops() == 0)
                .count() == ModelConstants.MAX_PLAYERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(final String input) {
        final Territory t = this.board.getGameTerritories().getTerritory(input);
        if (this.prepare) {
            this.board.placeTroops(t, 1);
            this.updatePreparation(t);
            return;
        }
        switch (this.phaseManager.getCurrentPhase()) {
            case PREPARATION:
                this.board.placeTroops(t, 1);
                this.checkGameState();
                this.controller.updateSquare(t.getName());
                if (this.controller.getCurrentPlayer().getTroops() == 0) {
                    this.selectedTerritories.clear();
                    this.phaseManager.switchToNextPhase();
                    this.controller.sendMessage(
                            new StringBuilder("You can now play your cards to gain bonus troops").toString());
                }
                break;
            case PLAY_CARDS:
                break;
            case COMBAT:
                this.selectedTerritories.add(t);
                if (this.selectedTerritories.size() == 1) {
                    this.setAvailableTerritories(t.getAdjTerritories().stream()
                            .filter(terr -> !this.controller.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(COMBAT_MESSAGE);
                } else {
                    final var result = this.board.instanceCombat(
                            new Pair<>(this.controller.getCurrentPlayer(), this.selectedTerritories.get(FIRST)),
                            new Pair<>(this.board.getAllPlayers().stream()
                                    .filter(p -> p.getTerritories().contains(this.selectedTerritories.get(SECOND)))
                                    .findAny().get(), this.selectedTerritories.get(SECOND)));
                    if (result.equals(GameBoard.CANCEL_COMBAT)) {
                        this.controller
                                .sendMessage(new StringBuilder("Canceled Combat, no modification applied.").toString());
                    } else {
                        this.controller.sendMessage(new StringBuilder("The attacker lost ")
                                .append(result.getX().getX())
                                .append(" troop")
                                .append(result.getX().getX() != 1 ? "s, " : ", ")
                                .append("and the defender lost ")
                                .append(result.getX().getY())
                                .append(" troop")
                                .append(result.getX().getY() != 1 ? "s." : '.')
                                .toString());
                    }
                    this.selectedTerritories.forEach(terr -> this.controller.updateSquare(terr.getName()));
                    if (result.getY()) {
                        this.board.playerDrawArmyCard(this.controller.getCurrentPlayer());
                        this.controller.getPlayerFromTerritory(this.selectedTerritories.get(SECOND).getName())
                                .removeTerritory(this.selectedTerritories.get(SECOND));
                        this.controller.getCurrentPlayer().addTerritory(this.board.getGameTerritories()
                                .getTerritory(this.selectedTerritories.get(SECOND).getName()));
                        this.controller.updateCards();
                        this.board.instanceMovement(this.selectedTerritories.get(FIRST),
                                this.selectedTerritories.get(SECOND));
                        this.selectedTerritories.forEach(terr -> this.controller.updateSquare(terr.getName()));
                        this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories().stream()
                                .filter(terr -> terr.getTroops() > 1).collect(Collectors.toSet()));
                        if (this.checkGameState()) {
                            break;
                        }
                    }
                    this.controller.sendMessage(RESET_COMBAT_MESSAGE);
                    this.selectedTerritories.clear();
                    this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories().stream()
                            .filter(terr -> terr.getTroops() > 1).collect(Collectors.toSet()));
                    this.selectedTerritories.forEach(terr -> this.controller.updateSquare(terr.getName()));
                }
                break;
            case MOVEMENT:
                this.selectedTerritories.add(t);
                if (this.selectedTerritories.size() == 1) {
                    this.setAvailableTerritories(t.getAdjTerritories().stream()
                            .filter(terr -> this.controller.getCurrentPlayer().getTerritories().contains(terr))
                            .collect(Collectors.toSet()));
                    this.controller.sendMessage(MOVEMENT_MESSAGE);
                } else {
                    this.board.instanceMovement(this.selectedTerritories.get(FIRST),
                            this.selectedTerritories.get(SECOND));
                    this.selectedTerritories.forEach(terr -> this.controller.updateSquare(terr.getName()));
                    this.selectedTerritories.clear();
                    this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories().stream()
                            .filter(terr -> terr.getTroops() > 1).collect(Collectors.toSet()));
                    this.checkGameState();
                    this.controller.sendMessage(RESET_MOVEMENT_MESSAGE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startCombat() {
        if (this.phaseManager.getCurrentPhase().equals(Phase.MOVEMENT)) {
            controller.sendMessage(new StringBuilder("Can't attack after switching to movement phase.").toString());
        } else {
            phaseManager.switchToPhase(Phase.COMBAT);
            controller.sendMessage(new StringBuilder("Select one of your territories").toString());
            this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories().stream()
                    .filter(t -> t.getTroops() > 1).collect(Collectors.toSet()));
            this.selectedTerritories.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMovement() {
        phaseManager.switchToPhase(Phase.MOVEMENT);
        controller.sendMessage(new StringBuilder("Select one of your territories").toString());
        this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories().stream()
                .filter(t -> t.getTroops() > 1).collect(Collectors.toSet()));
        this.selectedTerritories.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endPlayerTurn() {
        if (this.prepare) {
            this.controller
                    .sendMessage(new StringBuilder(
                            "Can't end your turn now, you have to finish placing 3 troops on your territories")
                            .toString());
            return;
        }
        this.phaseManager.switchToPhase(Phase.PREPARATION);
        this.turnManager.switchToNextPlayer();
        this.controller.updateInfo();
        this.board.defineBonusArmies(this.controller.getCurrentPlayer());
        this.selectedTerritories.clear();
        this.controller.sendMessage(new StringBuilder("It's Player").append(this.controller.getCurrentPlayer().getId())
                .append("'s turn.\nYou can now assign your bonus troops to your territories.\nBonus troops: ")
                .append(this.board.getPlayerFromId(this.turnManager.getCurrentPlayerID()).getTroops()).toString());
        this.controller.setCardController();
        this.controller.updateCards();
        this.setAvailableTerritories(this.controller.getCurrentPlayer().getTerritories());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvailableTerritories(final Set<Territory> territories) {
        this.disabledTerritories.clear();
        this.disabledTerritories.addAll(this.board.getGameTerritories().getTerritories());
        this.disabledTerritories.removeAll(territories);
        this.controller.enableAllTerritories();
        this.controller.disableTerritories(this.stringTerritories());
    }

    /**
     * Transforms the disabledTerritories to a list of Strings which are the name of
     * the territories.
     * 
     * @return disabled territories as a set of String
     */
    private Set<String> stringTerritories() {
        final Set<String> list = new HashSet<>();
        this.disabledTerritories.forEach(t -> list.add(t.getName()));
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBoard getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainController getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnManager getTurnManager() {
        return new TurnManagerImpl(this.turnManager);
    }

    /**
     * Checks if the game is over.
     * 
     * @return true if any player completed his objective, false otherwise
     */
    private boolean checkGameState() {
        if (this.gameState.isGameOver()) {
            this.controller.sendMessage(new StringBuilder("Player").append(this.gameState.getWinner().getId())
                    .append(" has won!").toString());
            this.controller.restartApp();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Territory> getSelectedTerritories() {
        return new ArrayList<>(this.selectedTerritories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Territory> getDisabledTerritories() {
        return new ArrayList<>(this.disabledTerritories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return this.gameState;
    }
}
