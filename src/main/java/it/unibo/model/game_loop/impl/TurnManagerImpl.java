package it.unibo.model.game_loop.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.model.dice.api.Dice;
import it.unibo.model.dice.impl.DiceImpl;
import it.unibo.model.game_loop.api.Pair;
import it.unibo.model.game_loop.api.TurnManager;
import it.unibo.model.player.api.Player;

/**
 * Simple implementation of TurnManager.
 */
public final class TurnManagerImpl implements TurnManager {

    private static final int DICE_FACES = 6;
    private final List<Player> players;

    private Iterator<Player> playerIterator;
    private Player currentPLayer;

    /**
     * Creates an iterator to cycle through the player list and sets the turn order.
     * @param players a list of players.
     */
    public TurnManagerImpl(final List<Player> players) {
        this.players = new LinkedList<>(players);
        playerIterator = this.players.iterator();
        setRandomOrder();
    }

    /**
     * Randomizes the order of players based on the dice throw.
     */
    private void setRandomOrder() {
        Dice d6 = new DiceImpl(DICE_FACES);
        List<Pair<Player, Integer>> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            list.add(new PairImpl<>(players.get(i), d6.roll()));
        }
        System.out.println(list);
        list.sort((p1, p2) -> p1.get2() < p2.get2() ? 1 : -1);
        for (int i = 0; i < players.size(); i++) {
            players.set(i, list.get(i).get1());
        }
        System.out.println(players);
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPLayer;
    }

    @Override
    public void switchToNextPlayer() {
        if (playerIterator.hasNext()) {
            currentPLayer = playerIterator.next();
        } else {
            playerIterator = players.iterator();
            currentPLayer = playerIterator.next();
        }
    }

    @Override
    public String toString() {
        Iterator<Player> it = players.iterator();
        String msg = "";
        String separator = "----------------------\n";
        int i = 1;
        while (it.hasNext()) {
            msg += separator + "TURN " + i + "\n" + it.next().toString() + "\n";
            i++;
        }
        return msg;
    }

}
