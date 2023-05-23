package it.unibo.model.gameloop.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.dice.api.Dice;
import it.unibo.model.dice.impl.DiceImpl;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.player.api.Player;

/**
 * Simple implementation of TurnManager.
 */
public final class TurnManagerImpl implements TurnManager {

    private static final int DICE_FACES = 6;
    private final List<Player> players;

    private Iterator<Player> playerIterator;
    private Player currentPlayer;

    /**
     * Creates an iterator to cycle through the player list and sets the turn order.
     * @param players a list of players.
     */
    public TurnManagerImpl(final Collection<Player> players) {
        this.players = new LinkedList<>(players);
        playerIterator = this.players.iterator();
        setRandomOrder();
        this.currentPlayer = playerIterator.next();
    }

    /**
     * Randomizes the order of players based on the dice throw.
     */
    private void setRandomOrder() {
        final Dice d6 = new DiceImpl(DICE_FACES);
        List<Pair<Player, Integer>> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            list.add(new Pair<>(players.get(i), d6.roll()));
        }
        list.sort((p1, p2) -> p1.getY() < p2.getY() ? 1 : -1);
        for (int i = 0; i < players.size(); i++) {
            players.set(i, list.get(i).getX());
        }
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchToNextPlayer() {
        if (!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        currentPlayer = playerIterator.next();
    }

    @Override
    public String toString() {
        Iterator<Player> it = players.iterator();
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
