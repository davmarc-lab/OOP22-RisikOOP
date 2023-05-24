package it.unibo.model.player.impl;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.api.Player.Color;
import it.unibo.model.player.api.PlayerBuilder;
import it.unibo.model.territory.api.Territory;

public final class PlayerBuilderImpl implements PlayerBuilder {

    private int id;
    private Deck<Territory> territoryDeck;
    private Hand<Army> playerHand;
    private Objective objective;
    private Color color;
    private int bonusTroops;

    public static PlayerBuilderImpl newBuilder() {
        return new PlayerBuilderImpl();
    }

    @Override
    public PlayerBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public PlayerBuilder territoryDeck(Deck<Territory> territoryDeck) {
        this.territoryDeck = territoryDeck;
        return this;
    }

    @Override
    public PlayerBuilder playerHand(Hand<Army> playerHand) {
        this.playerHand = playerHand;
        return this;
    }

    @Override
    public PlayerBuilder objective(Objective objective) {
        this.objective = objective;
        return this;
    }

    @Override
    public PlayerBuilder color(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public PlayerBuilder bonusTroops(int bonusTroops) {
        this.bonusTroops = bonusTroops;
        return this;
    }

    @Override
    public Player build() {
        return new PlayerImpl(this.id, this.territoryDeck, this.playerHand, this.objective, this.color,
                this.bonusTroops);
    }

}
