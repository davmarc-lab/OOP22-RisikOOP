package it.unibo.model.game_loop.impl;

import it.unibo.model.game_loop.api.Pair;

/**
 * Basic implementation of Pair.
 */
public class PairImpl<X,Y> implements Pair<X,Y> {

    private X val1;
    private Y val2;

    /**
     * Sets the initial values.
     * @param val1
     * @param val2
     */
    public PairImpl(final X val1, final Y val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public X get1() {
        return val1;
    }

    @Override
    public Y get2() {
        return val2;
    }

    @Override
    public void set1(final X val) {
        this.val1 = val;
    }

    @Override
    public void set2(final Y val) {
        this.val2 = val;
    }

    @Override
    public String toString() {
        return "[val1 --> " + val1 + "\n val2 --> " + val2 + "]";
    }

}
