package it.unibo.model.movement.impl;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.territory.api.Territory;

/**
 * Implementation of Movement.
 */
public class MovementImpl implements Movement {

    private Territory source;
    private Territory destination;

    /**
     * Constructor that prepares the movement by instantiating the territories involved.
     * 
     * @param oldTerritory the territory the troops are taken from
     * @param newTerritory the territory that will receive the troops
     */
    public MovementImpl(final Territory oldTerritory, final Territory newTerritory) {
        this.source = oldTerritory;
        this.destination = newTerritory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveTroops(final int troops) {
        if (this.isNumberValid(troops, this.source.getTroops())) {
            source.addTroops(-troops);
            destination.addTroops(troops);
        }
    }

    /**
     * Checks if after the movement the source territory will still have at least
     * one troop.
     * 
     * @param troops the number of troops to be sent
     * @param sourceTroops the number of troops on the source territory
     * @return true/false
     */
    private boolean isNumberValid(final int troops, final int sourceTroops) {
        return troops >= 1 && troops <= (sourceTroops - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getDestination() {
        return this.destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(final Territory source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestination(final Territory destination) {
        this.destination = destination;
    }

}
