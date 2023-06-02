package it.unibo.controller.reader.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.common.Pair;
import it.unibo.controller.controllerconstants.ControllerConstants;

/**
 * Json reader for the coordinates of the territories.
 */
public final class JsonReaderCoordinates extends AbstractFileReader<Set<Pair<String, List<Double>>>> {

    private static final String COORDINATES_PATH = new StringBuilder(ControllerConstants.RESOURCES_PATH).append("config")
            .append(ControllerConstants.PATH_SEPARATOR).append("territory").append(ControllerConstants.PATH_SEPARATOR)
            .append("Coordinates.json").toString();

    private Set<Pair<String, List<Double>>> territories;

    /**
     * Basic constructor.
     */
    public JsonReaderCoordinates() {
        super(COORDINATES_PATH);
        this.territories = new HashSet<>();
    }

    /**
     * Read territories from json file and creates the set of pairs including
     * territory's name and his list of coordinates and sizes, following the
     * Coordinates.json pattern.
     * 
     * @return the set of pairs of territory's name and his list of coordinates and
     *         sizes
     */
    @Override
    public Set<Pair<String, List<Double>>> readFromFile() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            final FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final String name = obj.get("name").toString();
                List<Double> values = new ArrayList<>();
                values.add(Double.parseDouble(obj.get("x").toString()));
                values.add(Double.parseDouble(obj.get("y").toString()));
                values.add(Double.parseDouble(obj.get("width").toString()));
                values.add(Double.parseDouble(obj.get("height").toString()));
                this.territories.add(new Pair<>(name, values));
            }
            fileInputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "File not found in the path given", e);
        } catch (ParseException e1) {
            this.getLogger().log(Level.SEVERE, "Excpetion in parsing the file", e1);
        }
        return Set.copyOf(this.territories);
    }

}
