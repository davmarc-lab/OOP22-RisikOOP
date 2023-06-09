package it.unibo.model.objective.api;

import java.util.Set;

/**
 * Represents the set of objectives of the game.
 */
public interface GameObjective {

    /**
     * Retrieves the set of objectives created by the factory.
     *
     * @return the set of objectives
     */
    Set<Objective> getSetObjectives();

    /**
     * Retrieves the default objective.
     * 
     * @return the default objective
     */
    Objective getDefaultObjective();
}
