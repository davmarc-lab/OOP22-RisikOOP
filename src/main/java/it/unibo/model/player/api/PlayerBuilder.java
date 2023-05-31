package it.unibo.model.player.api;

import it.unibo.model.army.api.Army;
import it.unibo.model.deck.api.Deck;
import it.unibo.model.hand.api.Hand;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.player.api.Player.Color;
import it.unibo.model.territory.api.Territory;

/**
 * The {@code PlayerBuild} interface represent a builder to create a player
 * object. It provides methods to set each {@link Player} fields.
 */
public interface PlayerBuilder {
    /**
     * Sets the player id.
     * 
     * @param id the player id
     * @return the player id
     */
    PlayerBuilder id(int id);

    /**
     * Sets the player territory deck.
     * 
     * @param territoryDeck the player territory deck
     * @return the player territory deck
     */
    PlayerBuilder territoryDeck(Deck<Territory> territoryDeck);

    /**
     * Sets the player {@code Hand<Army>}.
     * 
     * @param playerHand the player hand
     * @return the player hand
     */
    PlayerBuilder playerHand(Hand<Army> playerHand);

    /**
     * Sets the current player objective.
     * 
     * @param objective the player objective
     * @return the player objective
     */
    PlayerBuilder objective(Objective objective);

    /**
     * Sets the player color.
     * 
     * @param color the player color
     * @return the player color
     */
    PlayerBuilder color(Color color);

    /**
     * Sets the palyer bonus troops.
     * 
     * @param bonusTroops the player bonus troops
     * @return the player bonus troops
     */
    PlayerBuilder bonusTroops(int bonusTroops);

    /**
     * Construct a player with the fields setted.
     * 
     * @return the player object
     */
    Player build();
}
