package it.unibo.model.objective.impl;

import java.util.List;

import it.unibo.common.Pair;
import it.unibo.model.objective.api.Objective;

/**
 * Implementation of the Objective interface that represents a game objective.
 */
public class ObjectiveImpl implements Objective {

    private String description = "";
    private ObjectiveType objectiveType;
    private String armyColor = "";
    private String firstContinent = "";
    private String secondContinent = "";
    private Boolean thirdContinent = false;
    private int numTerritoriesToConquer;
    private int minNumArmies;
    private Boolean complete = false;
    private Pair<ObjectiveType, List<String>> checkObjectives;

    /**
     * Constructs a new ObjectiveImpl with the given armyColor and objective type.
     *
     * @param armyColor     the armyColor of the objective
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

    public ObjectiveImpl(final int numTerritoriesToConquer, final int minNumArmies, final ObjectiveType objectiveType) {
        this.numTerritoriesToConquer = numTerritoriesToConquer;
        this.minNumArmies = minNumArmies;
        this.objectiveType = objectiveType;
        this.description = createDescription();
    }

    public ObjectiveImpl() {
        this.description = "";
        this.objectiveType = ObjectiveType.NONE;
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
        return objectiveType;
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
    public String toString() {
        return this.description;
    }

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
            this.checkObjectives = new Pair<>(objectiveType, List.of(this.firstContinent, this.secondContinent));
            return new String(new StringBuilder("Conquer ").append(this.firstContinent).append(" and ")
                    .append(this.secondContinent));
        }
    }
}
