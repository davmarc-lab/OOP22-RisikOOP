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
     * Sets the territory that sends troops.
     * 
     * @param source territory
     */
    void setSource(Territory source);

    /**
     * Sets the territory that receives troops.
     * 
     * @param destination territory
     */
    void setDestination(Territory destination);

}
