package it.unibo.view.movement.logic;

public class TroopsLogicImpl implements TroopsLogic {

    private final int maxTroops;

    public TroopsLogicImpl(final int maxTroops) {
        this.maxTroops = maxTroops;
    }

    @Override
    public boolean isIncrementationValid(final int number) {
        return number < this.maxTroops - 1;
    }

    @Override
    public boolean isDecrementationValid(int number) {
        return number > 1;
    }

    @Override
    public boolean isNumberZero(int number) {
        return number == 0;
    }
    
}
