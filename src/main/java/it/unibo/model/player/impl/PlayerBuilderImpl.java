package it.unibo.model.player.impl;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.api.Player.Color;
import it.unibo.model.player.api.PlayerBuilder;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of {@link PlayerBuilder} that creates a pllayer object.
 */
public final class PlayerBuilderImpl implements PlayerBuilder {

    private int id;
    private Deck<Territory> territoryDeck;
    private Hand<Army> playerHand;
    private Objective objective;
    private Color color;
    private int bonusTroops;

    /**
     * Retrives a new {@code PlayerBuilderImpl} object.
     * 
     * @return player builder
     */
    public static PlayerBuilderImpl newBuilder() {
        return new PlayerBuilderImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder id(final int id) {
        this.id = id;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder territoryDeck(final Deck<Territory> territoryDeck) {
        this.territoryDeck = territoryDeck;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder playerHand(final Hand<Army> playerHand) {
        this.playerHand = playerHand;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder objective(final Objective objective) {
        this.objective = objective;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder color(final Color color) {
        this.color = color;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder bonusTroops(final int bonusTroops) {
        this.bonusTroops = bonusTroops;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player build() {
        return new PlayerImpl(this.id, this.territoryDeck, this.playerHand, this.objective, this.color,
                this.bonusTroops);
    }

}
