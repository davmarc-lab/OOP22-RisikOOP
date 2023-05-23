package it.unibo.model.gameloop.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.dice.api.Dice;
import it.unibo.model.dice.impl.DiceImpl;
import it.unibo.model.gameloop.api.TurnManager;

/**
 * Simple implementation of TurnManager.
 */
public final class TurnManagerImpl implements TurnManager {

    private static final int DICE_FACES = 6;
    private final List<Integer> playersIDs;

    private Iterator<Integer> playerIterator;
    private Integer currentPlayerID;

    /**
     * Creates an iterator to cycle through the player list and sets the turn order.
     * @param playersIDs a list of playersIDs.
     */
    public TurnManagerImpl(final List<Integer> playersIDs) {
        this.playersIDs = new LinkedList<>(playersIDs);
        playerIterator = this.playersIDs.iterator();
        setRandomOrder();
        this.currentPlayerID = playerIterator.next();
    }

    /**
     * Randomizes the order of playersIDs based on the dice throw.
     */
    private void setRandomOrder() {
        final Dice d6 = new DiceImpl(DICE_FACES);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < playersIDs.size(); i++) {
            list.add(new Pair<>(playersIDs.get(i), d6.roll()));
        }
        list.sort((p1, p2) -> p1.getY() < p2.getY() ? 1 : -1);
        for (int i = 0; i < playersIDs.size(); i++) {
            playersIDs.set(i, list.get(i).getX());
        }
    }

    @Override
    public Integer getCurrentPlayerID() {
        return currentPlayerID;
    }

    @Override
    public void switchToNextPlayer() {
        if (!playerIterator.hasNext()) {
            playerIterator = playersIDs.iterator();
        }
        currentPlayerID = playerIterator.next();
    }

    @Override
    public String toString() {
        Iterator<Integer> it = playersIDs.iterator();
        String msg = "";
        final String separator = "----------------------\n";
        int i = 1;
        while (it.hasNext()) {
            msg += separator + "TURN " + i + "\n" + it.next().toString() + "\n";
            i++;
        }
        return msg;
    }

}
