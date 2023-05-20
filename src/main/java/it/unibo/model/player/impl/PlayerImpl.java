package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Player interface.
 */
public class PlayerImpl implements Player {

    private int id;
    private final Deck<Territory> territories;
    private final Deck<Army> playerHandDeck;
    private Objective objective;
    private final Color color;

    /**
     * Empty constructor to create an empty {@code Player}.
     */
    public PlayerImpl() {
        this(0);
    }

    /**
     * Create a player from an specified id.
     * 
     * @param id player's id
     */
    public PlayerImpl(final int id) {
        this(id, new DeckImpl<>(), new DeckImpl<>(), new ObjectiveImpl("", Objective.ObjectiveType.NONE),
                Player.Color.BLACK);
    }

    /**
     * This constructor creates a player giving an id and {@code ColorPlayer}.
     * 
     * @param id    player's id
     * @param color player's color
     */
    public PlayerImpl(final int id, final Color color) {
        this(id, new DeckImpl<>(), new DeckImpl<>(), new ObjectiveImpl("None", Objective.ObjectiveType.NONE), color);
    }

    /**
     * Create a player form an id and Set of territories.
     * 
     * @param id          player's id
     * @param territories set of territories
     */
    public PlayerImpl(final int id, final Deck<Territory> territories) {
        this(id, territories, new DeckImpl<>(), new ObjectiveImpl("None", Objective.ObjectiveType.NONE), Color.BLACK);
    }

    public PlayerImpl(final Player p) {
        this(p.getId(), p.getTerritoryDeck(), p.getHandCards(), p.getObjective(), p.getColorPlayer());
    }

    /**
     * Full constructor of {@code Player}.
     * 
     * @param id             player's id
     * @param territories    player's territories
     * @param playerHandDeck player's personal deck
     * @param objective      player's objective
     * @param color          player's color
     */
    public PlayerImpl(final int id, final Deck<Territory> territories,
            final Deck<Army> playerHandDeck, final Objective objective, final Color color) {
        this.id = id;
        this.territories = territories;
        this.playerHandDeck = new DeckImpl<>(playerHandDeck.getDeck());
        this.objective = new ObjectiveImpl(objective.getDescription(), objective.getObjectiveType());
        this.color = color;
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
        return this.territories.getDeck().stream().filter(x -> x.equals(t)).findFirst().get().getArmy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTerritory(final Territory territory) {
        this.territories.getDeck().add(territory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTerritory(final Territory territory) {
        this.territories.getDeck().remove(territory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> getTerritories() {
        return new HashSet<>(this.territories.getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColorPlayer() {
        return this.color;
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
    public Deck<Army> getPlayerHand() {
        Deck<Army> x = new DeckImpl<>();
        x = this.playerHandDeck;
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Territory> getTerritoryDeck() {
        Deck<Territory> t = new DeckImpl<>();
        t = this.territories;
        return t;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObjective(final Objective objective) {
        this.objective = objective;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(
                new StringBuilder("ID -> ").append(this.getId()).append(", TERR -> ").append(this.getTerritories()));
    }
}
