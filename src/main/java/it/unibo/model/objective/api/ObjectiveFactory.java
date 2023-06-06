package it.unibo.model.objective.api;

/**
 * Represents a factory that creates objectives from a JSON file.
 */
public interface ObjectiveFactory {

    /**
     * Creates a set of objectives from a JSON file.
     * 
     * @return a new set of objectives
     */
    GameObjective createObjectiveSet();
}
