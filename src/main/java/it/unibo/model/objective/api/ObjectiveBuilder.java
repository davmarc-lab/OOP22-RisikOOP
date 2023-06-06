package it.unibo.model.objective.api;

import it.unibo.model.objective.api.Objective.ObjectiveType;

/**
 * Interface for the builder of the objective.
 */
public interface ObjectiveBuilder {

    /**
     * Sets the armyColor of the objective.
     * 
     * @param armyColor the armyColor of the objective
     * @return the builder
     */
    ObjectiveBuilder armyColor(String armyColor);

    /**
     * Sets the firstContinent of the objective.
     * 
     * @param firstContinent the firstContinent of the objective
     * @return the builder
     */
    ObjectiveBuilder firstContinent(String firstContinent);

    /**
     * Sets the secondContinent of the objective.
     * 
     * @param secondContinent the secondContinent of the objective
     * @return the builder
     */
    ObjectiveBuilder secondContinent(String secondContinent);

    /**
     * Sets the thirdContinent of the objective.
     * 
     * @param thirdContinent the thirdContinent of the objective
     * @return the builder
     */
    ObjectiveBuilder thirdContinent(Boolean thirdContinent);

    /**
     * Sets the numTerritoriesToConquer of the objective.
     * 
     * @param numTerritoriesToConquer the numTerritoriesToConquer of the objective
     * @return the builder
     */
    ObjectiveBuilder numTerritoriesToConquer(int numTerritoriesToConquer);

    /**
     * Sets the minNumArmies of the objective.
     * 
     * @param minNumArmies the minNumArmies of the objective
     * @return the builder
     */
    ObjectiveBuilder minNumArmies(int minNumArmies);

    /**
     * Sets the objectiveType of the objective.
     * 
     * @param objectiveType the objectiveType of the objective
     * @return the builder
     */
    ObjectiveBuilder objectiveType(ObjectiveType objectiveType);

    /**
     * Builds the objective.
     * 
     * @return the objective
     */
    Objective build();
}
