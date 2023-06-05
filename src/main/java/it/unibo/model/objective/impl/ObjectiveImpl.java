package it.unibo.model.objective.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.common.Pair;
import it.unibo.model.objective.api.Objective;

/**
 * Implementation of {@link Objective} interface.
 * It provides methods to set the status of the objective and all his attributes.
 */
public class ObjectiveImpl implements Objective, Cloneable {

    private final String description;
    private final ObjectiveType objectiveType;
    private final String armyColor;
    private final String firstContinent;
    private final String secondContinent;
    private final Boolean thirdContinent;
    private final int numTerritoriesToConquer;
    private final int minNumArmies;
    private Boolean complete = false;
    private Pair<ObjectiveType, List<String>> checkObjectives;

    /**
     * Constructs an {@code Objective} object with the given armyColor, firstContinent,
     * secondContinent, thirdContinent, numTerritoriesToConquer, minNumArmies and
     * objective type for the conquer objective.
     * 
     * @param armyColor
     * @param firstContinent
     * @param secondContinent
     * @param thirdContinent
     * @param numTerritoriesToConquer
     * @param minNumArmies
     * @param objectiveType
     */
    public ObjectiveImpl(final String armyColor, final String firstContinent, final String secondContinent,
            final Boolean thirdContinent, final int numTerritoriesToConquer, final int minNumArmies,
            final ObjectiveType objectiveType) {
        this.armyColor = armyColor;
        this.firstContinent = firstContinent;
        this.secondContinent = secondContinent;
        this.thirdContinent = thirdContinent;
        this.numTerritoriesToConquer = numTerritoriesToConquer;
        this.minNumArmies = minNumArmies;
        this.objectiveType = objectiveType;
        this.description = createDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isComplete() {
        return this.complete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComplete() {
        this.complete = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveType getObjectiveType() {
        return this.objectiveType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<ObjectiveType, List<String>> getCheckObjectives() {
        return this.checkObjectives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getCopy() {
        try {
            return (Objective) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(ObjectiveImpl.class.getName()).log(Level.OFF, "Cannot create a copy of the Object.");
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveImpl clone() throws CloneNotSupportedException {
        return (ObjectiveImpl) super.clone();
    }
 
    /**
     * Creates the description of the objective based on the objectiveType.
     * 
     * @return a string containing the description of the objective
     */
    private String createDescription() {
        if (objectiveType.equals(ObjectiveType.DESTROY)) {
            this.checkObjectives = new Pair<>(objectiveType, List.of(this.armyColor));
            return new String(new StringBuilder("Destroy the ").append(this.armyColor).append(" army"));
        }
        if (this.firstContinent.isEmpty()) {
            this.checkObjectives = new Pair<>(objectiveType,
                    List.of(Integer.toString(this.numTerritoriesToConquer), Integer.toString(this.minNumArmies)));
            return new String(
                    new StringBuilder("Conquer ").append(this.numTerritoriesToConquer)
                            .append(" territories with at least ")
                            .append(this.minNumArmies).append(this.minNumArmies > 1 ? " troops" : " troop"));
        }
        if (this.thirdContinent) {
            this.checkObjectives = new Pair<>(objectiveType,
                    List.of(this.firstContinent, this.secondContinent, this.thirdContinent.toString()));
            return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                    .append(this.secondContinent).append(" and another continent of your choice"));
        } else {
            this.checkObjectives = new Pair<>(objectiveType,
                    List.of(this.firstContinent, this.secondContinent, this.thirdContinent.toString()));
            return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                    .append(this.secondContinent));
        }
    }
}
