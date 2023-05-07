package it.unibo.model.objective.impl;

import it.unibo.model.objective.api.Objective;

public class ObjectiveImpl implements Objective{

    
    private String description;
    private ObjectiveType objectiveType;
    private Boolean complete = false;
    
    
    public ObjectiveImpl(String description, ObjectiveType objectiveType) {
        this.description = description;
        this.objectiveType = objectiveType;
    }

    @Override
    public Boolean isComplete() {
        return this.complete;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public ObjectiveType getObjectiveType() {
        return objectiveType;
    }
    
    @Override
    public String toString() {
        return "Objective [Description=" + description + ", Type=" + objectiveType + "]" + "\n";
    }

}
