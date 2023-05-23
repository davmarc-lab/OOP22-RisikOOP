package it.unibo.view.movement.logic;

public class TroopsLogicImpl implements TroopsLogic {

    private final int troopsInTerritory;

    public TroopsLogicImpl(final int troopsInTerritory) {
        this.troopsInTerritory = troopsInTerritory;
    }

    @Override
    public boolean isIncrementationValid() {
        return this.troopsInTerritory > 1;
    }
    
}
