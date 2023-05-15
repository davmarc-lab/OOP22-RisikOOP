package it.unibo.model.objective.api;

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
        DESTROY
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
     * Gets the type of the objective.
     *
     * @return the type of the objective
     */
    ObjectiveType getObjectiveType();

    /**
     * Gets the string representation of the objective.
     *
     * @return a string representation of the objective
     */
    @Override
    String toString();
}
