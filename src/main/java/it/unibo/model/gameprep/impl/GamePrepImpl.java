package it.unibo.model.gameprep.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.model.army.api.Army;
import it.unibo.model.army.impl.ArmyImpl;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

public class GamePrepImpl implements GamePrep {

    private static final int MAXCARDS = 42;
    private static final int TROOPS = 21;

    private List<Player> players = new ArrayList<>();
    private final ObjectiveFactory objectiveFactory = new ObjectiveFactoryImpl();
    private Deck<Objective> objectiveDeck;
    private final TerritoryFactory territoryFactory = new TerritoryFactoryImpl();
    private Deck<Territory> territoryDeck;
    private final Deck<Army> armyDeck = new DeckImpl<>();

    public GamePrepImpl() {
        this.createPlayers();
        this.createObjectiveDeck();
        this.createTerritoryDeck();
        this.createArmyDeck();
        this.assignObjectives();
        this.assignTerritories();
        this.assignTroops();
    }

    @Override
    public void createPlayers() {
        List<Player.Color> colors = Arrays.asList(Player.Color.values());
        Collections.shuffle(colors);
        IntStream.range(0, GameBoard.MAX_PLAYER)
                .mapToObj(i -> new PlayerImpl(i + 1, new DeckImpl<Territory>(), new DeckImpl<Army>(),
                        new ObjectiveImpl(), colors.get(i)))
                .forEach(this.players::add);
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
        Arrays.stream(Army.ArmyType.values())
                .forEach(armyType -> IntStream.range(0, MAXCARDS / GameBoard.MAX_PLAYER)
                        .forEach(i -> this.armyDeck.addCard(new ArmyImpl(armyType))));
        this.armyDeck.shuffle();
    }

    @Override
    public void assignTerritories() {
        this.getPlayers().forEach(player -> IntStream.range(0, MAXCARDS / GameBoard.MAX_PLAYER)
                .forEach(i -> player.addTerritory(this.territoryDeck.drawCard())));
    }

    @Override
    public void assignObjectives() {
        List<String> colors = this.getPlayers().stream().map(p -> p.getColorPlayer().getName()).toList();
        var unaviableColors = new ArrayList<>();
        for (var objective : objectiveDeck.getDeck()) {
            if (objective.getObjectiveType().equals(Objective.ObjectiveType.DESTROY)
                    && !colors.contains(objective.getDescription())) {
                unaviableColors.add(objective);
            }
        }
        objectiveDeck.getDeck().removeAll(unaviableColors);
        for (final Player player : this.getPlayers()) {
            Objective drawnObj = this.objectiveDeck.drawCard();
            if (drawnObj.getDescription().equals(player.getColorPlayer().getName())) {
                player.setObjective(this.objectiveFactory.getDefaulObjective());
            } else {
                player.setObjective(drawnObj);
            }
        }
    }

    @Override
    public void assignTroops() {
        this.getPlayers().stream().forEach(p -> p.addTroops(TROOPS));
        this.getPlayers().stream().forEach(p -> p.getTerritories().forEach(t -> t.addTroops(1)));
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

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }
}
