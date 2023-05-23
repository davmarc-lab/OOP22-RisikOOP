package it.unibo.model.hand.impl;

import it.unibo.model.army.api.Army;
import it.unibo.model.army.api.Army.ArmyType;

import java.util.ArrayList;
import java.util.List;

public class AbstractArmyHand extends HandImpl<Army> {

    private final List<Army> hand = new ArrayList<>();

    private final int PLAYABE_CARDS = 3;

    public AbstractArmyHand(List<Army> hand) {
        super(hand);
    }

    @Override
    public int playCards(List<Army> cards) {
        boolean allDifferent = cards.stream().distinct().count() == cards.size();
        boolean allSame = cards.stream().distinct().count() == 1;
        boolean playable = checkPlayableCards(cards);

        if (!playable) {
            return 0;
        }
        if (allSame) {
            this.hand.removeAll(cards);
            ArmyType armyType = cards.get(0).getArmyType();

            switch (armyType) {
                case ARTILLERY:
                    return 4;
                case INFANTRY:
                    return 6;
                case CAVALRY:
                    return 8;
                default:
                    return 0;
            }
        } else if (allDifferent) {
            this.hand.removeAll(cards);
            return 10;
        }
        return 0;
    }

    @Override
    protected boolean checkPlayableCards(List<Army> cards) {
        return cards.size() == PLAYABE_CARDS && this.hand.containsAll(cards);
    }
}
