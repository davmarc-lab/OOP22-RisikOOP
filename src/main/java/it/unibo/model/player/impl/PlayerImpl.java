package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.hand.impl.HandImpl;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of {@link Player} interface.
 * Provides method to interact with the player.
 */
public class PlayerImpl implements Player, Cloneable {

    private int id;
    private final Deck<Territory> territories;
    private final Hand playerHand;
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
            final Hand playerHandDeck, final Objective objective, final Color color, final int bonusTroops) {
        this.id = id;
        this.territories = new DeckImpl<>(territories.getDeck());
        this.playerHand = new HandImpl(playerHandDeck.getHand());
        this.objective = objective.getCopy();
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
        this.territories.addCard(territory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTerritory(final Territory territory) {
        this.territories.removeCard(territory);
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
        return this.objective.getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hand getPlayerHand() {
        return new HandImpl(this.playerHand.getHand());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCardToPlayerHand(final Army card) {
        this.playerHand.addCard(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int playCards(final List<Army> cards) {
        return this.playerHand.playCards(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Territory> getTerritoryDeck() {
        return new DeckImpl<>(this.territories.getDeck());
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
        this.objective = objective.getCopy();
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
    public void setObjectiveComplete() {
        this.objective.setComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(
                new StringBuilder("ID -> ").append(this.getId()).append(", TROOPS -> ").append(this.bonusTroops));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerImpl clone() throws CloneNotSupportedException {
        return (PlayerImpl) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCopy() {
        try {
            return (Player) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(PlayerImpl.class.getName()).log(Level.SEVERE, "Cannot create the copy of the object.");
        }
        throw new IllegalCallerException("Cannot create a copy.");
    }
}
