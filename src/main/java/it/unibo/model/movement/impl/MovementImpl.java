package it.unibo.model.movement.impl;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.territory.api.Territory;

public class MovementImpl implements Movement {

    private final Territory departure;
    private final Territory destination;

    public MovementImpl(final Territory oldTerritory, final Territory newTerritory) {
        this.departure = oldTerritory;
        this.destination = newTerritory;
    }

    @Override
    public void moveTroops(int troops) {
        departure.addTroops(-troops);
        destination.addTroops(troops);
    }

}
