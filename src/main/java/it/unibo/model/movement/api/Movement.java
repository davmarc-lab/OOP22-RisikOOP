package it.unibo.model.movement.api;

/**
 * This interface is used to instance troops movements between the territories
 * of a player.
 */
public interface Movement {

    /**
     * Moves troops from a territory to another.
     * 
     * @param troops the amount of troops to be moved
     */
    void moveTroops(int troops);

}
