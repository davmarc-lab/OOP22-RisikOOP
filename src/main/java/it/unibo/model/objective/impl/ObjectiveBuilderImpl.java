package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.Objective.ObjectiveType;
import it.unibo.model.objective.api.ObjectiveBuilder;

/**
 * Implementation of ObjectiveBuilder.
 */
public class ObjectiveBuilderImpl implements ObjectiveBuilder {

    private String armyColor = "";
    private String firstContinent = "";
    private String secondContinent = "";
    private Boolean thirdContinent;
    private int numTerritoriesToConquer;
    private int minNumArmies;
    private ObjectiveType objectiveType = ObjectiveType.NONE;

    /**
     * Private constructor.
     * 
     * @return a new instance of ObjectiveBuilderImpl
     */
    public static ObjectiveBuilder newBuilder() {
        return new ObjectiveBuilderImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder armyColor(final String armyColor) {
        this.armyColor = armyColor;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder firstContinent(final String firstContinent) {
        this.firstContinent = firstContinent;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder secondContinent(final String secondContinent) {
        this.secondContinent = secondContinent;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder thirdContinent(final Boolean thirdContinent) {
        this.thirdContinent = thirdContinent;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder numTerritoriesToConquer(final int numTerritoriesToConquer) {
        this.numTerritoriesToConquer = numTerritoriesToConquer;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder minNumArmies(final int minNumArmies) {
        this.minNumArmies = minNumArmies;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectiveBuilder objectiveType(final ObjectiveType objectiveType) {
        this.objectiveType = objectiveType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective build() {
        return new ObjectiveImpl(armyColor, firstContinent, secondContinent, thirdContinent, numTerritoriesToConquer,
                minNumArmies, objectiveType);
    }
}
