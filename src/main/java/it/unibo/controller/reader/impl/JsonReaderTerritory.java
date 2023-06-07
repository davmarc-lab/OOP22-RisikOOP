package it.unibo.controller.reader.impl;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryImpl;
import it.unibo.common.Pair;
import it.unibo.controller.controllerconstants.ControllerConstants;

/**
 * Class that extends the abstract file reader to read from 'Territories.json'.
 */
public final class JsonReaderTerritory extends AbstractFileReader<Set<Pair<String, Set<Territory>>>> {

    private static final String TERRITORIES_PATH = new StringBuilder(ControllerConstants.RESOURCES_PATH)
            .append("config")
            .append(ControllerConstants.PATH_SEPARATOR)
            .append("territory")
            .append(ControllerConstants.PATH_SEPARATOR)
            .append("Territories.json")
            .toString();

    private final Set<Pair<String, Set<Territory>>> territories;

    /**
     * Creates the {@code set of pairs of string and territories' set} where the
     * string is the continent.
     */
    public JsonReaderTerritory() {
        super(TERRITORIES_PATH);
        this.territories = new HashSet<>();
    }

    /**
     * Read territories from json file and creates the set of pairs including
     * continent's name and his set of territories, following the Territories.json
     * pattern.
     * 
     * @return the set of pairs of continent's name and his set of territories
     */
    @Override
    public Set<Pair<String, Set<Territory>>> readFromFile() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;

        try {
            final FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem: array) {
                obj = (JSONObject) elem;
                final String continentName = obj.get("continent").toString();
                final JSONArray terrArray = (JSONArray) obj.get("territories");
                this.territories.add(new Pair<>(continentName, new HashSet<>()));
                for (final Object t: terrArray) {
                    final JSONObject tObj = (JSONObject) t;
                    final String tName = tObj.get("name").toString();
                    this.territories.stream()
                            .filter(x -> continentName.equals(x.getX()))
                            .findAny()
                            .get()
                            .getY()
                            .add(new TerritoryImpl(tName));
                }
            }
            for (final Object elem: array) {
                obj = (JSONObject) elem;
                final JSONArray terrArray = (JSONArray) obj.get("territories");
                for (final Object t: terrArray) {
                    final JSONObject tObj = (JSONObject) t;
                    final String tName = tObj.get("name").toString();
                    final JSONArray adjArray = (JSONArray) tObj.get("adj");
                    for (final Object adjT: adjArray) {
                        this.getTerritory(tName).addAdjTerritory(this.getTerritory(adjT.toString()));
                    }
                }
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

    /**
     * Retrieves the territory with the given name.
     * 
     * @param name the name of the territory
     * @return the territory with the given name
     */
    private Territory getTerritory(final String name) {
        return this.getTerritories().stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findAny()
                .get();
    }

    /**
     * Retrieves the set of all territories.
     * 
     * @return the set of all territories
     */
    private Set<Territory> getTerritories() {
        final Set<Territory> set = new HashSet<>();
        this.territories.stream()
                .forEach(p -> set.addAll(p.getY()));
        return set;
    }
}
