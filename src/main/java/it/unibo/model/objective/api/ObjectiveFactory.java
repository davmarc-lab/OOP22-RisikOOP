package it.unibo.model.objective.api;

/**
 * Interface representing a factory for creating objectives.
 */
public interface ObjectiveFactory {

    /**
     * Creates a new set of objectives.
     */
    GameObjective createObjectiveSet();
}
