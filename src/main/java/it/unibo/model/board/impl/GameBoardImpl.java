package it.unibo.model.board.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.model.board.api.GameBoard;
import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.gameloop.impl.TurnManagerImpl;
import it.unibo.model.gameprep.impl.GamePrepImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

/**
 * Implementation of GameBoard, instance of the game table.
 */
public class GameBoardImpl implements GameBoard {

    private final List<Player> players = new ArrayList<>();
    private final GameTerritory gameTerritory = new TerritoryFactoryImpl().createTerritories();
    private final ObjectiveFactory objectiveFactory = new ObjectiveFactoryImpl();
    private final Deck<Army> armyDeck = new DeckImpl<>();

    private final TurnManager turnManager;

    /**
     * Prepare the {@code GameBoard} and inizialize all fields.
     */
    public GameBoardImpl() {
        final List<Player.Color> colors = Arrays.asList(Player.Color.values());
        Collections.shuffle(colors);
        IntStream.range(0, GameBoard.MAX_PLAYER)
                .mapToObj(i -> new PlayerImpl(i + 1, new DeckImpl<Territory>(), new DeckImpl<Army>(), new ObjectiveImpl(),
                        colors.get(i))).forEach(this.players::add);
        this.objectiveFactory.createObjectiveSet();
        new GamePrepImpl(this.players, this.gameTerritory, this.objectiveFactory, this.armyDeck);
        this.turnManager = new TurnManagerImpl(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instaceCombat(final Territory tStriker, final Territory tDefender) {
        // prova per controllare il funzionamento
        // var numStriker = 3;
        // tStriker.addArmy(10);
        // var numDefender = 3;
        // tDefender.addArmy(10);

        final Combat combat = new CombatImpl(tStriker, tDefender);
        // chiama le gui
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instanceMovement(final Territory oldTerritory, final Territory newTerritory) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Set<Territory>> getTerritories() {
        return new HashMap<>(this.gameTerritory.getTerritoryMap());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Army> getArmyDeck() {
        return new DeckImpl<>(this.armyDeck.getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Objective> getObjectives() {
        return new DeckImpl<>(this.objectiveFactory.getSetObjectives());
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
        return this.turnManager.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void defineBonusArmies() {
        var player = this.getCurrentPlayer();
        var continentsTroops = Set.of(BonusTroops.values());
        continentsTroops.forEach(
                t -> player.addTroops(player.getTerritories()
                        .containsAll(this.gameTerritory.getTerritoryMap().get(t.getContinent()))
                                ? t.getBonusTroops()
                                : 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void placeTroops() {

    }
}
