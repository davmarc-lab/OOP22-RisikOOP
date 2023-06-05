package it.unibo.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.controller.reader.impl.JsonReaderObjective;
import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;

/**
 * Implementation of {@link ObjectiveFactory} interface.
 * Creates objectives from a JSON file.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjective createObjectiveSet() {
        final JsonReaderObjective readerObjective = new JsonReaderObjective();
        final Set<Objective> objectives = new HashSet<>(readerObjective.readFromFile().getY());
        final Objective defaultObjective = readerObjective.readFromFile().getX();
        return new GameObjectiveImpl(objectives, defaultObjective);
    }
}
