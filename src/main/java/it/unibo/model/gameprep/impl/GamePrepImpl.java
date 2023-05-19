package it.unibo.model.gameprep.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.army.impl.ArmyImpl;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

public class GamePrepImpl implements GamePrep {

    private final ObjectiveFactory objectiveFactory = new ObjectiveFactoryImpl();
    private Deck<Objective> objectiveDeck;
    private final TerritoryFactory territoryFactory = new TerritoryFactoryImpl();
    private Deck<Territory> territoryDeck;
    private final Deck<Army> armyDeck = new DeckImpl<>();

    public GamePrepImpl() {
        this.createObjectiveDeck();
        this.createTerritoryDeck();
        this.createArmyDeck();
    }

    @Override
    public void createObjectiveDeck() {
        this.objectiveFactory.createObjectiveSet();
        this.objectiveDeck = new DeckImpl<>(this.objectiveFactory.getSetObjectives());
        this.objectiveDeck.shuffle();
    }

    @Override
    public void createTerritoryDeck() {
        this.territoryFactory.createTerritories();
        this.territoryDeck = new DeckImpl<>(this.territoryFactory.getTerritories());
        this.territoryDeck.shuffle();
    }

    @Override
    public void createArmyDeck() {
        for (final Army.ArmyType armyType : Army.ArmyType.values()) {
            for (int i = 0; i < 14; i++) {
                this.armyDeck.addCard(new ArmyImpl(armyType));
            }
        }
        this.armyDeck.shuffle();
    }

    @Override
    public Deck<Objective> getObjectiveDeck() {
        return this.objectiveDeck;
    }

    @Override
    public Deck<Territory> getTerritoryDeck() {
        return this.territoryDeck;
    }

    @Override
    public Map<String, Set<Territory>> getTerritoryMap() {
        return new HashMap<>(this.territoryFactory.getTerritoryMap());
    }

    @Override
    public Deck<Army> getArmyDeck() {
        return this.armyDeck;
    }
}
