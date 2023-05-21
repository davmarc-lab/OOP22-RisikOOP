package it.unibo.model.gameloop.impl;

import it.unibo.model.gameloop.api.PhaseManager;

/**
 * Implementation of PhaseManager.
 */
public final class PhaseManagerImpl implements PhaseManager {

    private Phase currentPhase;

    /**
     * The constructor sets the current phase to PREPARATION because it's the first
     * phase of a turn.
     */
    public PhaseManagerImpl() {
        currentPhase = Phase.PREPARATION;
    }

    @Override
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    @Override
    public void switchToNextPhase() {
        currentPhase = Phase.values()[(currentPhase.ordinal() % Phase.values().length) + 1];
    }

    @Override
    public String toString() {
        return "[Current phase --> " + currentPhase + "]";
    }

}
