package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.model.player.api.ColorPlayer;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Player interface.
 */
public class PlayerImpl implements Player {

    private int id;
    private Set<Territory> territories = new HashSet<>();
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
        this.color = color;
    }

    /**
     * Create a player form an id and Set of territories.
     * 
     * @param id player's id
     * @param territories set of territories
     */
    public PlayerImpl(final int id, final Set<Territory> territories) {
        this(id);
        this.territories = territories;
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
        return this.territories;
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
    public String toString() {
        return new String("ID -> " + this.getId() + "\nNumTerritory -> " + this.getTerritories().size()
            + "\nTerritories -> " + this.getTerritories());
    }
}
