package it.unibo.controller.reader.impl;

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

import it.unibo.common.Pair;
import it.unibo.controller.controllerconstants.ControllerConstants;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.Objective.ObjectiveType;
import it.unibo.model.objective.impl.ObjectiveBuilderImpl;

/**
 * Json reader for the objectives.
 */
public final class JsonReaderObjective extends AbstractFileReader<Pair<Objective, Set<Objective>>> {

    private static final String OBJECTIVES_PATH = new StringBuilder(ControllerConstants.RESOURCES_PATH)
            .append("config")
            .append(ControllerConstants.PATH_SEPARATOR)
            .append("objective").append(ControllerConstants.PATH_SEPARATOR).append("Objectives.json").toString();

    private final Set<Objective> objectives;

    /**
     * Empty constructor that prepares an empty set of objectives that will be
     * filled later.
     */
    public JsonReaderObjective() {
        super(OBJECTIVES_PATH);
        this.objectives = new HashSet<>();
    }

    /**
     * Read objectives from json file and creates a set of objectives and the
     * default objective.
     * 
     * @return a pair containing the default objective and the set of objectives.
     */
    @Override
    public Pair<Objective, Set<Objective>> readFromFile() {
        Objective defaultObjective = ObjectiveBuilderImpl.newBuilder().objectiveType(ObjectiveType.NONE).build();
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
                final JSONArray defaultObj = (JSONArray) obj.get("defaultObj");
                for (final Object destroyElem : destroyArray) {
                    final Objective objective = ObjectiveBuilderImpl.newBuilder().armyColor(destroyElem.toString())
                            .objectiveType(Objective.ObjectiveType.DESTROY).build();
                    this.objectives.add(objective);
                }
                for (final Object conquerElem : conquerArray) {
                    final JSONObject x = (JSONObject) conquerElem;
                    final JSONArray cArray = (JSONArray) x.get("scope");
                    if (cArray.size() == 2) {
                        final Objective objective = ObjectiveBuilderImpl.newBuilder()
                                .numTerritoriesToConquer(Integer.parseInt(cArray.get(0).toString()))
                                .minNumArmies(Integer.parseInt(cArray.get(1).toString()))
                                .objectiveType(Objective.ObjectiveType.CONQUER).build();
                        this.objectives.add(objective);
                    } else {
                        final Objective objective = ObjectiveBuilderImpl.newBuilder()
                                .firstContinent(cArray.get(0).toString()).secondContinent(cArray.get(1).toString())
                                .thirdContinent(Boolean.valueOf(cArray.get(2).toString()))
                                .objectiveType(Objective.ObjectiveType.CONQUER).build();
                        this.objectives.add(objective);
                    }
                }
                for (final Object defObj : defaultObj) {
                    final JSONObject z = (JSONObject) defObj;
                    final JSONArray dArray = (JSONArray) z.get("scope");
                    defaultObjective = ObjectiveBuilderImpl.newBuilder()
                            .numTerritoriesToConquer(Integer.parseInt(dArray.get(0).toString()))
                            .minNumArmies(Integer.parseInt(dArray.get(1).toString()))
                            .objectiveType(Objective.ObjectiveType.CONQUER).build();
                    this.objectives.add(defaultObjective);

                }
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (ParseException | IOException e) {
            this.getLogger().log(Level.SEVERE, "Error parsing Objectives.json", e);
        }
        return new Pair<Objective, Set<Objective>>(defaultObjective, this.objectives);
    }
}
