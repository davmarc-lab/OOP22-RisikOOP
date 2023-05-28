package it.unibo.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.controller.reader.impl.JsonReaderObjective;
import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;

/**
 * Implementation of the ObjectiveFactory interface that creates objectives from
 * a JSON file.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {
    /**
     * Creates a set of objectives from a JSON file.
     */
    @Override
    public GameObjective createObjectiveSet() {
        final JsonReaderObjective readerObjective = new JsonReaderObjective();
        final Set<Objective> objectives = new HashSet<>(readerObjective.readFromFile().getY());
        final Objective defaultObjective = readerObjective.readFromFile().getX();
        return new GameObjectiveImpl(objectives, defaultObjective);
    }
}
