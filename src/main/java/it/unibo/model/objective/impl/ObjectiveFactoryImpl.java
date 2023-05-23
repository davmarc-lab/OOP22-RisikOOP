package it.unibo.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.controller.JsonReaderObjective;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;

/**
 * Implementation of the ObjectiveFactory interface that creates objectives from
 * a JSON file.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {

    private final Set<Objective> objectives;
    private Objective defaultObjective;
    private final JsonReaderObjective readerObjective;

    public ObjectiveFactoryImpl() {
        this.readerObjective = new JsonReaderObjective();
        this.objectives = new HashSet<>();
        this.defaultObjective = new ObjectiveImpl();
    }

    /**
     * Creates a set of objectives from a JSON file.
     */
    @Override
    public void createObjectiveSet() {
        this.objectives.addAll(this.readerObjective.readFromFile().getY());
        this.defaultObjective = this.readerObjective.readFromFile().getX();
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
