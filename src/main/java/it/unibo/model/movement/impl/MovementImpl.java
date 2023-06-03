package it.unibo.model.movement.impl;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryImpl;

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
        this.source = new TerritoryImpl(oldTerritory);
        this.destination = new TerritoryImpl(newTerritory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getSource() {
        return new TerritoryImpl(this.source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getDestination() {
        return new TerritoryImpl(this.destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(final Territory source) {
        this.source = new TerritoryImpl(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestination(final Territory destination) {
        this.destination = new TerritoryImpl(destination);
    }

}
