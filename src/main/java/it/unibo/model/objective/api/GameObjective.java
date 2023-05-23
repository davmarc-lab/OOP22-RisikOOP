package it.unibo.model.objective.api;

import java.util.Set;

public interface GameObjective {

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
