package it.unibo.model.objective.api;

import java.util.Set;

/**
 * Represents the set of objectives of the game.
 */
public interface GameObjective {

    /**
     * Gets the set of objectives created by the factory.
     *
     * @return the set of objectives
     */
    Set<Objective> getSetObjectives();

    /**
     * Gets the default objective.
     * 
     * @return the default objective
     */
    Objective getDefaultObjective();
}
