package it.unibo.model.player.api;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player.Color;
import it.unibo.model.territory.api.Territory;

public interface PlayerBuilder {
    
    PlayerBuilder id(int id);

    PlayerBuilder territoryDeck(Deck<Territory> territoryDeck);

    PlayerBuilder playerHand(Hand<Army> playerHand);

    PlayerBuilder objective(Objective objective);

    PlayerBuilder color(Color color);

    PlayerBuilder bonusTroops(int bonusTroops);

    Player build();
}
