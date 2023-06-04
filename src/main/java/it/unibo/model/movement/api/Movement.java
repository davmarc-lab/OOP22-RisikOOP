package it.unibo.model.movement.api;

import it.unibo.model.territory.api.Territory;

/**
 * This interface is used to instance troops movements between the territories
 * of a player.
 */
public interface Movement {

    /**
     * @return the territory that sends troops
     */
    Territory getSource();

    /**
     * @return the territory that receives troops
     */
    Territory getDestination();

    /**
     * Checks if the movement is possible
     * 
     * @return true if the source territory would have at least one troop after
     *         movement, false otherwise
     */
    boolean isMovementValid();

}
