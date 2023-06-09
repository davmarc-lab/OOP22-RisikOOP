package it.unibo.controller.reader.impl;

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

/**
 * This class is used to read the coordinates for the squares that contain the
 * troops on the territories.
 */
public final class JsonReaderSquareCoordinates extends AbstractFileReader<Set<Pair<String, Pair<Double, Double>>>> {

    private static final String SQUARES_PATH = "/config/territory/SquareCoordinates.json";

    private final Set<Pair<String, Pair<Double, Double>>> squares;

    /**
     * Basic constructor.
     */
    public JsonReaderSquareCoordinates() {
        super(SQUARES_PATH);
        this.squares = new HashSet<>();
    }

    /**
     * Read territories from json file and creates the set of pairs including
     * territory's name and the positions.
     * 
     * @return the set of pairs of territory's name and his list of coordinates and
     *         sizes
     */
    @Override
    public Set<Pair<String, Pair<Double, Double>>> readFromFile() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(
                    this.getClass().getResourceAsStream(SQUARES_PATH), 
                    StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final String name = obj.get("name").toString();
                final double x = Double.parseDouble(obj.get("x").toString());
                final double y = Double.parseDouble(obj.get("y").toString());
                this.squares.add(new Pair<>(name, new Pair<>(x, y)));
            }
            inputStreamReader.close();
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "File not found in the path given", e);
        } catch (ParseException e1) {
            this.getLogger().log(Level.SEVERE, "Excpetion in parsing the file", e1);
        }
        return Set.copyOf(this.squares);
    }
}
