package it.unibo.gameengine.impl;

import it.unibo.gameengine.api.PhaseManager;

/**
 * Implementation of {@link PhaseManager}.
 * Used to manage all the phases of a turn.
 */
public class PhaseManagerImpl implements PhaseManager {

    private Phase currentPhase;

    /**
     * Constructor that sets the current phase to PREPARATION because it's the first
     * phase of a turn.
     */
    public PhaseManagerImpl() {
        currentPhase = Phase.REINFORCEMENTS;
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
