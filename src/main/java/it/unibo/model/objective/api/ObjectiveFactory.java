package it.unibo.model.objective.api;

import java.util.Set;

/**
 * Interface representing a factory for creating objectives.
 */
public interface ObjectiveFactory {

    /**
     * Creates a new set of objectives.
     */
    void createObjectiveSet();

    /**
     * Gets the set of objectives created by this factory.
     *
     * @return the set of objectives
     */
    Set<Objective> getSetObjectives();

    /**
     * Gets the default objective.
     * 
     * @return the default objective
     */
    Objective getDefaulObjective();
}
