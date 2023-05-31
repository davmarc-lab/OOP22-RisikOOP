package it.unibo.model.movement.impl;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.territory.api.Territory;

public class MovementImpl implements Movement {

    private Territory source;
    private Territory destination;

    public MovementImpl(final Territory oldTerritory, final Territory newTerritory) {
        this.source = oldTerritory;
        this.destination = newTerritory;
    }

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

    @Override
    public Territory getSource() {
        return this.source;
    }

    @Override
    public Territory getDestination() {
        return this.destination;
    }

    @Override
    public void setSource(final Territory source) {
        this.source = source;
    }

    @Override
    public void setDestination(final Territory destination) {
        this.destination = destination;
    }

}
