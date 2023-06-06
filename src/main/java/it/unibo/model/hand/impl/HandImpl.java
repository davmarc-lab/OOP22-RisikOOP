package it.unibo.model.hand.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.army.api.Army;
import it.unibo.model.hand.api.Hand;

/**
 * Implementation of the {@link Hand} interface.
 * Represents the hand of a player.
 */
public class HandImpl implements Hand {

    private static final int PLAYABLE_CARDS = 3;
    private static final int ARTILLERY_TROOPS = 4;
    private static final int INFANTRY_TROOPS = 6;
    private static final int CAVALRY_TROOPS = 8;
    private static final int DIFFERENT_CARDS_TROOPS = 10;

    private final List<Army> hand;

    /**
     * Constructs a HandImpl object with the specified initial hand.
     * 
     * @param hand the initial hand
     */
    public HandImpl(final List<Army> hand) {
        this.hand = new ArrayList<>(hand);
    }

    /**
     * Constructs a HandImpl object with an empty hand.
     */
    public HandImpl() {
        this.hand = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final Army card) {
        this.hand.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Army> getHand() {
        return new ArrayList<>(this.hand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int playCards(final List<Army> cards) {
        if (!checkPlayableCards(cards)) {
            return 0;
        }
        if (cards.stream().map(c -> c.getArmyType()).distinct().count() == 1) {
            this.removeFromHand(cards);

            switch (cards.get(0).getArmyType()) {
                case ARTILLERY:
                    return ARTILLERY_TROOPS;
                case INFANTRY:
                    return INFANTRY_TROOPS;
                case CAVALRY:
                    return CAVALRY_TROOPS;
                default:
                    return 0;
            }
        } else if (cards.stream().map(c -> c.getArmyType()).distinct().count() == cards.size()) {
            this.removeFromHand(cards);
            return DIFFERENT_CARDS_TROOPS;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPlayableCards(final List<Army> cards) {
        return cards.size() == PLAYABLE_CARDS && this.checkIfInHand(cards);
    }

    /**
     * Checks if the given list of cards is in the hand.
     * 
     * @param cards the list of cards to check
     * @return {@code true} if the cards are in the hand, {@code false} otherwise
     */
    private boolean checkIfInHand(final List<Army> cards) {
        return cards.stream()
                .map(Army::getArmyType)
                .anyMatch(cardInHand -> hand.stream().anyMatch(card -> card.getArmyType().equals(cardInHand)));
    }

    /**
     * Removes the given list of cards from the hand.
     * 
     * @param cards the list of cards to remove
     */
    private void removeFromHand(final List<Army> cards) {
        cards.forEach(c -> this.hand
                .remove(this.hand.stream().filter(x -> x.getArmyType().equals(c.getArmyType())).findAny().get()));
    }
}
