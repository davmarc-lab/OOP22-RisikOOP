package it.unibo.model.game_loop.api;

/**
 * This class is used to save a couple of 2 different values.
 */
public interface Pair<X,Y> {

    /**
     * @return the first value
     */
    X get1();

    /**
     * @return the second value
     */
    Y get2();

    /**
     * Sets the first value.
     * @param val
     */
    void set1(X val);

    /**
     * Sets the second value.
     * @param val
     */
    void set2(Y val);
    
}
