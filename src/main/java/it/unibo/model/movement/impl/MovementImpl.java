package it.unibo.model.movement.impl;

import java.util.stream.Collectors;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryImpl;

/**
 * Implementation of {@link Movement}.
 * Provides methods to instance troops movements between the territories of a
 * player and checks the validity of the movement.
 */
public class MovementImpl implements Movement {

    private final Territory source;
    private final Territory destination;
    private final int troops;

    /**
     * Constructor that prepares the movement by instantiating the territories
     * involved.
     * 
     * @param oldTerritory the territory the troops are taken from
     * @param newTerritory the territory that will receive the troops
     * @param troops       the number of troops to be sent
     */
    public MovementImpl(final Territory oldTerritory, final Territory newTerritory, final int troops) {
        this.source = new TerritoryImpl(oldTerritory);
        this.destination = new TerritoryImpl(newTerritory);
        this.troops = troops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovementValid() {
        return this.source.getTroops() - this.troops >= 1 && this.source.getAdjTerritories().stream()
                .map(t -> t.getName()).collect(Collectors.toSet()).contains(this.destination.getName());
    }
}
