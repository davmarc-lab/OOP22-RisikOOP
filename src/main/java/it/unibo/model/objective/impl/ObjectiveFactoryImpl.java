package it.unibo.model.objective.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.api.ObjectiveFactory;

/**
 * Implementation of the ObjectiveFactory interface that creates objectives from
 * a JSON file.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {

    private final Set<Objective> objectives;

    private final String pathSeparator = System.getProperty("file.separator");

    private final Logger logger = Logger.getLogger(ObjectiveFactoryImpl.class.getName());

    /**
     * Constructs a new ObjectiveFactoryImpl.
     */
    public ObjectiveFactoryImpl() {
        this.objectives = new HashSet<>();
    }

    /**
     * Creates a set of objectives from a JSON file.
     */
    @Override
    public void createObjectiveSet() {
        final JSONParser parser = new JSONParser();
        try {
            final String filePath = "src" + pathSeparator + "main" + pathSeparator + "resources" + pathSeparator
                    + "Objectives.json";
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                final JSONObject obj = (JSONObject) elem;
                final JSONArray destroyArray = (JSONArray) obj.get("destroyObj");
                final JSONArray conquerArray = (JSONArray) obj.get("conquerObj");
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
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (ParseException | IOException e) {
            logger.log(Level.SEVERE, "Error parsing Objectives.json", e);
        }
    }

    /**
     * Returns an unmodifiable set of objectives created by this factory.
     *
     * @return an unmodifiable set of objectives
     */
    @Override
    public Set<Objective> getSetObjectives() {
        return Collections.unmodifiableSet(this.objectives);
    }
}
