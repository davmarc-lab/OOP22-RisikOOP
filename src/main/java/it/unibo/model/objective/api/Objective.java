package it.unibo.model.objective.api;

import java.util.List;

import it.unibo.common.Pair;

/**
 * Interface representing a game objective.
 */
public interface Objective {

    /**
     * Enum representing the type of objective.
     */
    enum ObjectiveType {
        /**
         * Objective to conquer a territories.
         */
        CONQUER,
        /**
         * Objective to destroy another player.
         */
        DESTROY,
        /**
         * No objective.
         */
        NONE
    }

    /**
     * Checks whether the objective has been completed.
     *
     * @return true if the objective is complete, false otherwise
     */
    Boolean isComplete();

    /**
     * Sets the objective as complete.
     */
    void setComplete();

    /**
     * Gets a description of the objective.
     *
     * @return the description of the objective
     */
    String getDescription();

    /**
     * Gets the check objectives which are used to check if the objective is
     * complete.
     * 
     * @return a pair containing the type of the objective and a list of strings
     */
    Pair<ObjectiveType, List<String>> getCheckObjectives();

    /**
     * Gets the type of the objective.
     *
     * @return the type of the objective
     */
    ObjectiveType getObjectiveType();
}
