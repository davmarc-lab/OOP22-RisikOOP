package it.unibo.model.objective.impl;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.*;

import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;

public class ObjectiveFactoryImpl implements ObjectiveFactory{

    private Set<Objective> objectives;

    public ObjectiveFactoryImpl() {
        this.objectives = new HashSet<>();
    }
    @Override
    public void createObjectiveSet() {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            JSONArray array = (JSONArray)parser.parse(new FileReader("src/main/resources/config/objective/Objectives.json"));
            for (final Object elem: array) {
                obj = (JSONObject)elem;
                JSONArray destroyArray = (JSONArray)obj.get("destroyObj");
                JSONArray conquerArray = (JSONArray)obj.get("conquerObj");
                for (final Object destroyElem : destroyArray) {
                    ObjectiveImpl objective = new ObjectiveImpl(destroyElem.toString(), Objective.ObjectiveType.DESTROY);
                    this.objectives.add(objective);
                }
                for (final Object conquerElem : conquerArray) {
                    ObjectiveImpl objective = new ObjectiveImpl(conquerElem.toString(), Objective.ObjectiveType.CONQUER);
                    this.objectives.add(objective);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ObjectiveFactoryImpl ofi = new ObjectiveFactoryImpl();
        ofi.createObjectiveSet();
        System.out.println(ofi.objectives);
    }
    @Override
    public Set<Objective> getSetObjectives() {
        return this.objectives;
    }
}
