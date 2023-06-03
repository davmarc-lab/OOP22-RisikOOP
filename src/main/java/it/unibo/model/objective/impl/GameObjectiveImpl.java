package it.unibo.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;

/**
 * Implementation of the {@link GameObjective} interface.
 */
public class GameObjectiveImpl implements GameObjective {

    private final Set<Objective> objectives;
    private Objective defaultObjective;

    /**
     * Constructs a new instance of GameObjectiveImpl with the specified
     * objectives and default objective.
     *
     * @param objectives       the set of objectives
     * @param defaultObjective the default objective
     */
    public GameObjectiveImpl(final Set<Objective> objectives, final Objective defaultObjective) {
        this.objectives = new HashSet<>(objectives);
        this.defaultObjective = defaultObjective.getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Objective> getSetObjectives() {
        return Set.copyOf(this.objectives);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getDefaultObjective() {
        return this.defaultObjective.getCopy();
    }
}
