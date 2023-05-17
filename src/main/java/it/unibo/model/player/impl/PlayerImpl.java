package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.ColorPlayer;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Player interface.
 */
public class PlayerImpl implements Player {

    private final int id;
    private final Set<Territory> territories;
    private final Deck<Army> playerHandDeck;
    private final Objective objective;
    private final ColorPlayer color;

    /**
     * Create a player from an specified id.
     * 
     * @param id player's id
     */
    public PlayerImpl(final int id) {
        this(id, new HashSet<>(), new DeckImpl<>(), new ObjectiveImpl(), new ColorPlayerImpl());
    }

    /**
     * This constructor creates a player giving an id and {@code ColorPlayer}.
     * 
     * @param id player's id
     * @param color player's color
     */
    public PlayerImpl(final int id, final ColorPlayer color) {
        this(id, new HashSet<>(), new DeckImpl<>(), new ObjectiveImpl(), color);
    }

    /**
     * Create a player form an id and Set of territories.
     * 
     * @param id player's id
     * @param territories set of territories
     */
    public PlayerImpl(final int id, final Set<Territory> territories) {
        this(id, territories, new DeckImpl<>(), new ObjectiveImpl(), new ColorPlayerImpl());
    }

    /**
     * Create a player form an id and deck.
     * 
     * @param id player's id
     * @param deck player's cards in a {@code Deck}
     */
    public PlayerImpl(final int id, final Deck<Army> deck) {
        this(id, new HashSet<>(), deck, new ObjectiveImpl(), new ColorPlayerImpl());
    }

    /**
     * Full constructor of {@code Player}.
     * 
     * @param id player's id
     * @param territories player's territories
     * @param playerHandDeck player's personal deck
     * @param objective player's objective
     * @param color player's color
     */
    public PlayerImpl(final int id, final Set<Territory> territories, 
       final Deck<Army> playerHandDeck, final Objective objective, final ColorPlayer color) {
        this.id = id;
        this.territories = new HashSet<>(territories);
        this.playerHandDeck = new DeckImpl<>(playerHandDeck.getDeck());
        this.objective = new ObjectiveImpl(objective.getDescription(), objective.getObjectiveType());
        this.color = new ColorPlayerImpl(color);
}

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArmy(final Territory t) {
        return this.territories.stream().filter(x -> x.equals(t)).findFirst().get().getArmy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTerritory(final Stream<Territory> territory) {
        this.territories.addAll(territory.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTerritory(final Stream<Territory> territory) {
        this.territories.removeAll(territory.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getTerritories() {
        return Set.copyOf(this.territories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorPlayer getColorPlayer() {
        return new ColorPlayerImpl(this.color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Army> getHandCards() {
        return new DeckImpl<>(this.playerHandDeck.getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getObjective() {
        return new ObjectiveImpl(this.objective.getDescription(), this.objective.getObjectiveType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(new StringBuilder("ID -> ").append(this.getId()).append("\nNumTerritory -> ")
            .append(this.getTerritories().size()).append("\nTerritories -> ").append(this.getTerritories()));
    }
}
