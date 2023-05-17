package it.unibo.model.board.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.model.board.api.GameBoard;
import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.gameprep.impl.GamePrepImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of GameBoard, instance of the game table.
 */
public class GameBoardImpl implements GameBoard  {

    private final Map<String, Set<Territory>> territoriesMap;
    private final Deck<Army> armyDeck;
    private final Deck<Territory> territoryDeck;
    private final Deck<Objective> objectiveDeck;

    /**
     * Prepare the {@code GameBoard} and inizialize all fields.
     */
    public GameBoardImpl() {
        final GamePrep gamePrep = new GamePrepImpl();
        this.territoriesMap = gamePrep.getTerritoriesMap();
        this.armyDeck = gamePrep.getArmyDeck();
        this.territoryDeck = gamePrep.getTerritoryDeck();
        this.objectiveDeck = gamePrep.getObjectiveDeck();
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
}
