package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;

/**
 * Implementation of the Objective interface that represents a game objective.
 */
public class ObjectiveImpl implements Objective {

    private String description;
    private ObjectiveType objectiveType;
    private String armyColor;
    private String firstContinent = "";
    private String secondContinent;
    private Boolean thirdContinent = false;
    private int numTerritories;
    private int minNumArmies;
    private Boolean complete = false;

    /**
     * Constructs a new ObjectiveImpl with the given armyColor and objective type.
     *
     * @param armyColor        the armyColor of the objective
     * @param objectiveType the type of the objective
     */
    public ObjectiveImpl(final String armyColor, final ObjectiveType objectiveType) {
        this.armyColor = armyColor;
        this.objectiveType = objectiveType;
        this.description = createDescription();
    }

    public ObjectiveImpl(final String firstContinent, final String secondContinent, final ObjectiveType objectiveType) {
        this.firstContinent = firstContinent;
        this.secondContinent = secondContinent;
        this.objectiveType = objectiveType;
        this.description = createDescription();
    }

    public ObjectiveImpl(final String firstContinent, final String secondContinent, final Boolean thirdContinent,
            final ObjectiveType objectiveType) {
        this.firstContinent = firstContinent;
        this.secondContinent = secondContinent;
        this.thirdContinent = thirdContinent;
        this.objectiveType = objectiveType;
        this.description = createDescription();
    }

    public ObjectiveImpl(final int numTerritories, final int minNumArmies, final ObjectiveType objectiveType) {
        this.numTerritories = numTerritories;
        this.minNumArmies = minNumArmies;
        this.objectiveType = objectiveType;
        this.description = createDescription();
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
        return this.description;
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

    private String createDescription() {
        if (objectiveType.equals(ObjectiveType.DESTROY)) {
            return new String(new StringBuilder("Destroy the ").append(this.armyColor).append(" army"));
        }
        if (this.firstContinent.isEmpty()) {
            return new String(
                    new StringBuilder("Conquer ").append(this.numTerritories).append(" territories with at least ")
                            .append(this.minNumArmies).append(this.minNumArmies > 1 ? " troops" : " troop"));
        }
        if (this.thirdContinent) {
            return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                    .append(this.secondContinent).append(" and another continent of your choice"));
        } else {
            return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                    .append(this.secondContinent));
        }
    }

    /**
     * Gets a string representation of this objective.
     *
     * @return a string representation of this objective
     */
    @Override
    public String toString() {
        return this.description;
    }
}
