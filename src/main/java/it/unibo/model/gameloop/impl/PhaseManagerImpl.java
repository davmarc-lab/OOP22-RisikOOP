package it.unibo.model.gameloop.impl;

import it.unibo.model.gameloop.api.PhaseManager;

/**
 * Implementation of PhaseManager.
 */
public class PhaseManagerImpl implements PhaseManager {

    private Phase currentPhase;

    /**
     * The constructor sets the current phase to PREPARATION because it's the first
     * phase of a turn.
     */
    public PhaseManagerImpl() {
        currentPhase = Phase.PREPARATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToNextPhase() {
        currentPhase = Phase.values()[(currentPhase.ordinal() + 1) % Phase.values().length];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToPhase(final Phase phase) {
        this.currentPhase = phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[Current phase --> " + currentPhase + "]";
    }

}
