package it.unibo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.common.Constants;
import it.unibo.common.Pair;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveImpl;

public class JsonReaderObjective extends AbstractFileReader<Pair<Objective, Set<Objective>>> {

    private Set<Objective> objectives;
    private Objective defaultObjective;

    public JsonReaderObjective() {
        super(Constants.OBJECTIVES_PATH);
        this.objectives = new HashSet<>();
    }

    @Override
    public Pair<Objective, Set<Objective>> readFromFile() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;

        try {
            final FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final JSONArray destroyArray = (JSONArray) obj.get("destroyObj");
                final JSONArray conquerArray = (JSONArray) obj.get("conquerObj");
                final String defaultObj = obj.get("defaultObj").toString();
                for (final Object destroyElem : destroyArray) {
                    final ObjectiveImpl objective = new ObjectiveImpl(destroyElem.toString(),
                            Objective.ObjectiveType.DESTROY);
                    this.objectives.add(objective);
                }
                for (final Object conquerElem : conquerArray) {
                    final ObjectiveImpl objective = new ObjectiveImpl(conquerElem.toString(),
                            Objective.ObjectiveType.CONQUER);
                    this.objectives.add(objective);
                }
                this.defaultObjective = new ObjectiveImpl(defaultObj.toString(),
                        Objective.ObjectiveType.CONQUER);
                this.objectives.add(defaultObjective);
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (ParseException | IOException e) {
            this.getLogger().log(Level.SEVERE, "Error parsing Objectives.json", e);
        }
        return new Pair<Objective, Set<Objective>>(this.defaultObjective, this.objectives);
    }
}
