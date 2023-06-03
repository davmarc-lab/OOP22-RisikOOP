package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.Objective.ObjectiveType;
import it.unibo.model.objective.api.ObjectiveBuilder;

public class ObjectiveBuilderImpl implements ObjectiveBuilder {

    private String armyColor = "";
    private String firstContinent = "";
    private String secondContinent = "";
    private Boolean thirdContinent;
    private int numTerritoriesToConquer;
    private int minNumArmies;
    private ObjectiveType objectiveType = ObjectiveType.NONE;

    public static ObjectiveBuilder newBuilder() {
        return new ObjectiveBuilderImpl();
    }

    @Override
    public ObjectiveBuilder armyColor(String armyColor) {
        this.armyColor = armyColor;
        return this;
    }

    @Override
    public ObjectiveBuilder firstContinent(String firstContinent) {
        this.firstContinent = firstContinent;
        return this;
    }

    @Override
    public ObjectiveBuilder secondContinent(String secondContinent) {
        this.secondContinent = secondContinent;
        return this;
    }

    @Override
    public ObjectiveBuilder thirdContinent(Boolean thirdContinent) {
        this.thirdContinent = thirdContinent;
        return this;
    }

    @Override
    public ObjectiveBuilder numTerritoriesToConquer(int numTerritoriesToConquer) {
        this.numTerritoriesToConquer = numTerritoriesToConquer;
        return this;
    }

    @Override
    public ObjectiveBuilder minNumArmies(int minNumArmies) {
        this.minNumArmies = minNumArmies;
        return this;
    }

    @Override
    public ObjectiveBuilder objectiveType(ObjectiveType objectiveType) {
        this.objectiveType = objectiveType;
        return this;
    }

    @Override
    public Objective build() {
        return new ObjectiveImpl(armyColor, firstContinent, secondContinent, thirdContinent, numTerritoriesToConquer,
                minNumArmies, objectiveType);
    }
}
