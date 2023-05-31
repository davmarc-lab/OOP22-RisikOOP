package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.hand.impl.AbstractArmyHand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of {@link Player} interface.
 */
public class PlayerImpl implements Player {

    private int id;
    private final Deck<Territory> territories;
    private final Hand<Army> playerHand;
    private Objective objective;
    private final Color color;
    private int bonusTroops;

    /**
     * Full constructor of {@code Player}.
     * 
     * @param id             player's id
     * @param territories    player's territories
     * @param playerHandDeck player's personal deck
     * @param objective      player's objective
     * @param color          player's color
     * @param bonusTroops    player's bonus troops
     */
    public PlayerImpl(final int id, final Deck<Territory> territories,
            final Hand<Army> playerHandDeck, final Objective objective, final Color color, final int bonusTroops) {
        this.id = id;
        this.territories = territories;
        this.playerHand = new AbstractArmyHand(playerHandDeck.getHand());
        this.objective = new ObjectiveImpl(objective.getDescription(), objective.getObjectiveType());
        this.color = color;
        this.bonusTroops = bonusTroops;
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
        return this.territories.getDeck().stream().filter(x -> x.equals(t)).findFirst().get().getTroops();
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
    public Objective getObjective() {
        return new ObjectiveImpl(this.objective.getDescription(), this.objective.getObjectiveType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hand<Army> getPlayerHand() {
        return this.playerHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Territory> getTerritoryDeck() {
        return this.territories;
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
    public void addTroops(final int numberTroops) {
        this.bonusTroops += numberTroops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTroops() {
        return this.bonusTroops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(
                new StringBuilder("ID -> ").append(this.getId()).append(", TROOPS -> ").append(this.bonusTroops));
    }
}
