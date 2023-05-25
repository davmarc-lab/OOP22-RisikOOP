package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;

/**
 * Implementation of the Objective interface that represents a game objective.
 */
public class ObjectiveImpl implements Objective {

    private String description;
    private ObjectiveType objectiveType;
    private String firstContinent;
    private String secondContinent;
    private Boolean thirdContinent = false;
    private int territories;
    private int minNumArmies;
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

    public ObjectiveImpl(final String firstContinent, final String secondContinent, final ObjectiveType objectiveType) {
        this.firstContinent = firstContinent;
        this.secondContinent = secondContinent;
        this.objectiveType = objectiveType;
    }

    public ObjectiveImpl(final String firstContinent, final String secondContinent, final Boolean thirdContinent,
            final ObjectiveType objectiveType) {
        this.firstContinent = firstContinent;
        this.secondContinent = secondContinent;
        this.thirdContinent = thirdContinent;
        this.objectiveType = objectiveType;
    }

    public ObjectiveImpl(final int territories, final int minNumArmies, final ObjectiveType objectiveType) {
        this.territories = territories;
        this.minNumArmies = minNumArmies;
        this.objectiveType = objectiveType;
    }

    public ObjectiveImpl() {
        this.description = "";
        this.objectiveType = ObjectiveType.NONE;
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
        if (objectiveType.equals(ObjectiveType.DESTROY)) {
            return new String(new StringBuilder("Destroy the ").append(this.description).append(" army"));
        } else {
            if (this.thirdContinent) {
                return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                        .append(this.secondContinent).append(" and ").append(thirdContinent));
            } else {
                return new String(new StringBuilder("Conquer ").append(this.territories).append(" territories with at least ")
                        .append(this.minNumArmies).append(" armies"));
            }
        }
    }
}
