package it.unibo.model.objective.api;

import java.util.Set;

public interface ObjectiveFactory {

    void createObjectiveSet();

    Set<Objective> getSetObjectives();

}
