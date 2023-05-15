package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;

/**
 * Implementation of the Objective interface that represents a game objective.
 */
public class ObjectiveImpl implements Objective {

    private final String description;
    private final ObjectiveType objectiveType;
    private Boolean complete = false;

    /**
     * Constructs a new ObjectiveImpl with the given description and objective type.
     *
     * @param description   the description of the objective
     * @param objectiveType the type of the objective
     */
    public ObjectiveImpl(final String description, final ObjectiveType objectiveType) {
        this.description = description;
        this.objectiveType = objectiveType;
    }

    /**
     * Returns whether this objective has been completed or not.
     *
     * @return true if this objective is complete, false otherwise
     */
    @Override
    public Boolean isComplete() {
        return this.complete;
    }

    /**
     * Sets this objective as completed.
     */
    @Override
    public void setComplete() {
        this.complete = true;
    }

    /**
     * Gets the description of this objective.
     *
     * @return the description of this objective
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type of this objective.
     *
     * @return the type of this objective
     */
    @Override
    public ObjectiveType getObjectiveType() {
        return objectiveType;
    }

    /**
     * Gets a string representation of this objective.
     *
     * @return a string representation of this objective
     */
    @Override
    public String toString() {
        return "Objective [Description=" + description + ", Type=" + objectiveType + "]" + "\n";
    }
}
