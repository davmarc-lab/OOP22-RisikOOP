package it.unibo.model.objective.api;

public interface Objective {

    enum ObjectiveType {
        CONQUER,
        DESTROY
    }

    Boolean isComplete();

    String getDescription();

    ObjectiveType getObjectiveType();

    String toString();

}
