package it.unibo.model.board.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.controller.combat.api.CombatController;
import it.unibo.controller.combat.impl.CombatControllerView;
import it.unibo.controller.movement.api.MovementController;
import it.unibo.controller.movement.impl.MovementControllerView;
import it.unibo.model.army.api.Army;
import it.unibo.model.army.impl.ArmyImpl;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.gameloop.impl.TurnManagerImpl;
import it.unibo.model.gameprep.impl.GamePrepImpl;
import it.unibo.model.hand.impl.AbstractArmyHand;
import it.unibo.model.movement.impl.MovementImpl;
import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerBuilderImpl;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

/**
 * Implementation of {@link GameBoard} interface.
 */
public class GameBoardImpl implements GameBoard {

    private final List<Player> players = new ArrayList<>();
    private final GameTerritory gameTerritory = new TerritoryFactoryImpl().createTerritories();
    private final GameObjective gameObjective = new ObjectiveFactoryImpl().createObjectiveSet();
    private final Deck<Army> armyDeck = new DeckImpl<>();
    private Deck<Objective> objectiveDeck;
    private final TurnManager turnManager;

    /**
     * Constructor of {@code GameBoardImpl} that inizialize all fields.
     */
    public GameBoardImpl() {
        createPlayers();
        createArmyDeck();
        createObjectiveDeck();
        final Pair<Deck<Objective>, Objective> pairObjective = new Pair<>(this.objectiveDeck,
                this.gameObjective.getDefaulObjective());
        new GamePrepImpl(this.players, new DeckImpl<>(this.gameTerritory.getTerritories()), pairObjective)
                .preparePlayers();
        this.objectiveDeck.setDeck(pairObjective.getX().getDeck());
        this.turnManager = new TurnManagerImpl(this.players.stream().map(Player::getId).toList());
    }

    /**
     * Creates all players.
     */
    private void createPlayers() {
        final List<Player.Color> colors = Arrays.asList(Player.Color.values());
        Collections.shuffle(colors);
        IntStream.range(0, Constants.MAX_PLAYERS)
                .mapToObj(i -> PlayerBuilderImpl.newBuilder().id(i + 1).territoryDeck(new DeckImpl<>())
                        .playerHand(new AbstractArmyHand()).objective(new ObjectiveImpl()).color(colors.get(i))
                        .bonusTroops(0).build())
                .forEach(this.players::add);
    }

    /**
     * Creates the army deck.
     */
    private void createArmyDeck() {
        Arrays.stream(Army.ArmyType.values())
                .forEach(armyType -> IntStream.range(0, Constants.MAX_CARDS_FOR_EACH_PLAYER / GameBoard.MAX_PLAYER)
                        .forEach(i -> this.armyDeck.addCard(new ArmyImpl(armyType))));
        this.armyDeck.shuffle();
    }

    /**
     * Creates the objective deck.
     */
    private void createObjectiveDeck() {
        this.objectiveDeck = new DeckImpl<>(this.gameObjective.getSetObjectives());
        this.objectiveDeck.shuffle();
    }

    /**
     * Retrieves a player from an id.
     * 
     * @param id players's id
     * @return player
     */
    private Player getPlayerFromId(final int id) {
        return this.players.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instanceCombat(final Pair<Player, Territory> attacker, final Pair<Player, Territory> defender) {
        final CombatController ccAttacker = new CombatControllerView(attacker);
        ccAttacker.startPopup();
        final CombatController ccDefender = new CombatControllerView(defender);
        ccDefender.startPopup();
        new CombatImpl(attacker.getY(), defender.getY()).attack(ccAttacker.getCombatOutcome(),
                ccDefender.getCombatOutcome());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instanceMovement(final Territory oldTerritory, final Territory newTerritory) {
        final MovementController mc = new MovementControllerView(new Pair<>(oldTerritory, newTerritory));
        mc.startPopup();
        new MovementImpl(oldTerritory, newTerritory).moveTroops(mc.getFinalResult());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Army> getArmyDeck() {
        return this.armyDeck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Objective> getObjectives() {
        return this.objectiveDeck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Territory> getTerritoryDeck() {
        return new DeckImpl<>(this.gameTerritory.getTerritories());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameTerritory getGameTerritories() {
        return this.gameTerritory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getAllPlayers() {
        return this.players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.getPlayerFromId(this.turnManager.getCurrentPlayerID());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void defineBonusArmies() {
        final Player player = this.getCurrentPlayer();
        final int conquestsTroops = Long.valueOf(player.getTerritories().stream().count()).intValue()
                / Constants.BONUS_TROOPS_DIVIDER;
        final Set<BonusTroops> continentsTroops = Set.of(BonusTroops.values());
        continentsTroops.forEach(
                t -> player.addTroops(player.getTerritories()
                        .containsAll(this.gameTerritory.getTerritoryMap().get(t.getContinent()))
                                ? t.getBonusTroops()
                                : 0));
        player.addTroops(conquestsTroops);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void placeTroops(final Territory territory, final int troops) {
        territory.addTroops(troops);
        this.getCurrentPlayer().addTroops(-troops);
    }
}
