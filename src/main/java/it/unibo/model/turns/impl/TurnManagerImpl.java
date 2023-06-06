package it.unibo.model.turns.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.dice.api.Dice;
import it.unibo.model.dice.impl.DiceImpl;
import it.unibo.model.turns.api.TurnManager;

/**
 * Implementation of {@link TurnManager}.
 * Manages the players and the turn order.
 */
public class TurnManagerImpl implements TurnManager {

    private static final int DICE_FACES = 6;
    private final List<Integer> playersIDs;

    private Iterator<Integer> playerIterator;
    private Integer currentPlayerID;

    /**
     * Constructor that creates an iterator to cycle through the player list and
     * sets the turn order.
     * 
     * @param playersIDs a list of playersIDs.
     */
    public TurnManagerImpl(final List<Integer> playersIDs) {
        this.playersIDs = new LinkedList<>(playersIDs);
        playerIterator = this.playersIDs.iterator();
        setRandomOrder();
        this.currentPlayerID = playerIterator.next();
    }

    /**
     * Constructor used for copies of TurnManagerImpl.
     * 
     * @param tm the turn manager from which we will copy its parameters
     */
    public TurnManagerImpl(final TurnManager tm) {
        this.currentPlayerID = tm.getCurrentPlayerID();
        this.playersIDs = new ArrayList<>(tm.getPlayersId());
        this.playerIterator = tm.getIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Integer> getIterator() {
        final Iterator<Integer> copy = this.playersIDs.iterator();
        while (copy.hasNext()) {
            if (copy.next().equals(this.currentPlayerID)) {
                return copy;
            }
        }
        throw new IllegalAccessError("Impossible to find player ID");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getPlayersId() {
        return new ArrayList<>(this.playersIDs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToNextPlayer() {
        if (!playerIterator.hasNext()) {
            playerIterator = playersIDs.iterator();
        }
        currentPlayerID = playerIterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final Iterator<Integer> it = playersIDs.iterator();
        final StringBuilder msg = new StringBuilder(50);
        final String separator = "----------------------\n";
        int i = 1;
        while (it.hasNext()) {
            msg.append(separator).append("TURN ").append(String.valueOf(i)).append("\n\tPlayer")
                    .append(it.next().toString()).append('\n');
            i++;
        }
        return msg.toString();
    }

    /**
     * Randomizes the order of playersIDs based on the dice throw.
     */
    private void setRandomOrder() {
        final Dice d6 = new DiceImpl(DICE_FACES);
        final List<Pair<Integer, Integer>> list = new ArrayList<>();
        this.playersIDs.forEach(id -> list.add(new Pair<>(id, d6.roll())));
        Collections.shuffle(list);
        for (int i = 0; i < playersIDs.size(); i++) {
            playersIDs.set(i, list.get(i).getX());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetTurns() {
        this.playerIterator = this.playersIDs.iterator();
        this.currentPlayerID = playerIterator.next();
    }
}
