package it.unibo.model.movement.api;

import it.unibo.model.territory.api.Territory;

/**
 * The {@code Movement} interface provides methods to instance troops movements
 * between the territories of a player nad checks the validity of the movement.
 */
public interface Movement {

    /**
     * Retrieves the source territory.
     * 
     * @return the territory that sends troops
     */
    Territory getSource();

    /**
     * Retrieves the destination territory.
     * 
     * @return the territory that receives troops
     */
    Territory getDestination();

    /**
     * Checks if the movement is possible.
     * 
     * @return true if the source territory would have at least one troop after
     *         movement, false otherwise
     */
    boolean isMovementValid();

}
