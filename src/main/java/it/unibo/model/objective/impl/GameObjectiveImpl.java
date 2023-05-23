package it.unibo.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;

public class GameObjectiveImpl implements GameObjective {

    private final Set<Objective> objectives;
    private final Objective defaultObjective;

    public GameObjectiveImpl(final Set<Objective> objectives, final Objective defaultObjective) {
        this.objectives = new HashSet<>(objectives);
        this.defaultObjective = defaultObjective;
    }

    /**
     * Returns an unmodifiable set of objectives created by this factory.
     *
     * @return an unmodifiable set of objectives
     */
    @Override
    public Set<Objective> getSetObjectives() {
        return Set.copyOf(this.objectives);
    }

    @Override
    public Objective getDefaulObjective() {
        return this.defaultObjective;
    }
}
