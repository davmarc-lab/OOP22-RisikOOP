package it.unibo.model.hand.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.army.api.Army;
import it.unibo.model.hand.api.Hand;

/**
 * Implementation of the {@link Hand} interface representing the hand of a
 * player.
 */
public abstract class HandImpl implements Hand {

    private static final int PLAYABLE_CARDS = 3;
    private static final int ARTILLERY_TROOPS = 4;
    private static final int INFANTRY_TROOPS = 6;
    private static final int CAVALRY_TROOPS = 8;
    private static final int DIFFERENT_CARDS_TROOPS = 10;

    private List<Army> hand = new ArrayList<>();
    
    /**
     * Constructs a HandImpl object with the specified initial hand.
     * 
     * @param hand the initial hand
     */
    public HandImpl(final List<Army> hand) {
        this.hand = hand;
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
        return this.hand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int playCards(final List<Army> cards) {

        if (!checkPlayableCards(cards)) {
            return 0;
        }
        if (cards.stream().distinct().count() == 1) {
            this.hand.removeAll(cards);

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
        } else if (cards.stream().distinct().count() == cards.size()) {
            this.hand.removeAll(cards);
            return DIFFERENT_CARDS_TROOPS;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPlayableCards(final List<Army> cards) {
        return cards.size() == PLAYABLE_CARDS && this.hand.containsAll(cards);
    }
}
