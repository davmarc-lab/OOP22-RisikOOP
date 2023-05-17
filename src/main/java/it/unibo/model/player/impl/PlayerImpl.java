package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.model.deck.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.player.api.ColorPlayer;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Player interface.
 */
public class PlayerImpl implements Player {

    private int id;
    private Set<Territory> territories = new HashSet<>();
    private Deck<Army> playerHandDeck = new DeckImpl<>();
    private ColorPlayer color = new ColorPlayerImpl();

    /**
     * Create a player from an specified id.
     * 
     * @param id player's id
     */
    public PlayerImpl(final int id) {
        this.id = id;
    }

    /**
     * This constructor creates a player giving an id and {@code ColorPlayer}.
     * 
     * @param id player's id
     * @param color player's color
     */
    public PlayerImpl(final int id, final ColorPlayer color) {
        this(id);
        this.color = new ColorPlayerImpl(color);
    }

    /**
     * Create a player form an id and Set of territories.
     * 
     * @param id player's id
     * @param territories set of territories
     */
    public PlayerImpl(final int id, final Set<Territory> territories) {
        this(id);
        this.territories = Set.copyOf(territories);
    }

    /**
     * Create a player form an id and deck.
     * 
     * @param id player's id
     * @param deck player's cards in a {@code Deck}
     */
    public PlayerImpl(final int id, final Deck<Army> deck) {
        this(id);
        this.playerHandDeck = new DeckImpl<>(deck.getDeck());
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
    public String toString() {
        return new String(new StringBuilder("ID -> " + this.getId() + "\nNumTerritory -> " + this.getTerritories().size()
        + "\nTerritories -> " + this.getTerritories()));
    }
}
