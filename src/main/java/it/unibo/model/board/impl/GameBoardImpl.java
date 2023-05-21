package it.unibo.model.board.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.model.board.api.GameBoard;
import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.gameloop.impl.TurnManagerImpl;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.gameprep.impl.GamePrepImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of GameBoard, instance of the game table.
 */
public class GameBoardImpl implements GameBoard {

    private final Map<String, Set<Territory>> territoriesMap;
    private final Deck<Army> armyDeck;
    private final Deck<Territory> territoryDeck;
    private final Deck<Objective> objectiveDeck;
    private final List<Player> players;
    private final TurnManager turnManager;
    private final GamePrep gamePrep = new GamePrepImpl();

    /**
     * Prepare the {@code GameBoard} and inizialize all fields.
     */
    public GameBoardImpl() {
        this.players = this.gamePrep.getPlayers();
        this.territoriesMap = this.gamePrep.getTerritoryMap();
        this.armyDeck = this.gamePrep.getArmyDeck();
        this.territoryDeck = this.gamePrep.getTerritoryDeck();
        this.objectiveDeck = this.gamePrep.getObjectiveDeck();
        this.turnManager = new TurnManagerImpl(new ArrayList<>(this.players));
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
        return new HashMap<>(this.territoriesMap);
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
        return new DeckImpl<>(this.objectiveDeck.getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Territory> getTerritoryDeck() {
        return new DeckImpl<>(this.territoryDeck.getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnManager getTurnManager() {
        TurnManager tm = new TurnManagerImpl(List.of());
        tm = this.turnManager;
        return tm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getAllPlayers() {
        return new ArrayList<>(this.players);
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
        // var player = this.getCurrentPlayer();
        // var continentsTroops = Set.of(BonusTroops.values());
        // continentsTroops.forEach(
        //         t -> player.addTroops(player.getTerritories()
        //                 .containsAll(this.gamePrep.getTerritoryMap().get(t.getContinent()))
        //                         ? t.getBonusTroops()
        //                         : 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void placeTroops() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeTroops'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBoard getCopy() {
        GameBoard gb = new GameBoardImpl();
        gb = this;
        return gb;
    }
}
