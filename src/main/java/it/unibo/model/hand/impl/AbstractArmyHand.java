package it.unibo.model.hand.impl;

import it.unibo.model.army.api.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a hand of armies.
 */
public class AbstractArmyHand extends HandImpl<Army> {

    private final List<Army> hand = new ArrayList<>();

    private static final int PLAYABLE_CARDS = 3;
    private static final int ARTILLERY_TROOPS = 4;
    private static final int INFANTRY_TROOPS = 6;
    private static final int CAVALRY_TROOPS = 8;
    private static final int DIFFERENT_CARDS_TROOPS = 10;

    /**
     * Constructs an AbstractArmyHand object with the specified initial hand.
     *
     * @param hand the initial hand of armies
     */
    public AbstractArmyHand(final List<Army> hand) {
        super(hand);
    }

    /**
     * Constructs an AbstractArmyHand object with an empty hand.
     */
    public AbstractArmyHand() {
        super();
    }

    /**
     * Plays the given cards from the hand and calculates the number of troops for
     * the player.
     *
     * @param cards the list of armies to play
     * @return the number of troops obtained by playing different combinations of
     *         cards, or 0 if the cards are not playable
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
     * Checks if the given cards are playable from the hand.
     *
     * @param cards the list of armies to check
     * @return true if the cards are playable, false otherwise
     */
    @Override
    protected boolean checkPlayableCards(final List<Army> cards) {
        return cards.size() == PLAYABLE_CARDS && this.hand.containsAll(cards);
    }
}
