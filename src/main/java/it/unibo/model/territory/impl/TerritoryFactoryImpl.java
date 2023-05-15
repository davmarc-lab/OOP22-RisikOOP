package it.unibo.model.territory.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;

/**
 * Implementation of the territory factory.
 */
public final class TerritoryFactoryImpl implements TerritoryFactory {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR
            + "config" + PATH_SEPARATOR + "territory" + PATH_SEPARATOR + "Territories.json";

    private Map<String, Set<Territory>> territories;
    private final String path;

    /**
     * Creates the map of all territories from the default file.
     */
    public TerritoryFactoryImpl() {
        this(PATH);
    }

    /**
     * Creates the map of all territories from the file in the path given.
     * 
     * @param p
     *          path of the file
     */
    public TerritoryFactoryImpl(final String p) {
        this.path = p;
        this.territories = new HashMap<>();
    }

    @Override
    public void createTerritories() throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();

        try {
            final FileInputStream fileInputStream = new FileInputStream(this.path);
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem: array) {
                obj = (JSONObject) elem;
                String continentName = obj.get("continent").toString();
                JSONArray terrArray = (JSONArray) obj.get("territories");
                this.territories.put(continentName, new HashSet<>());
                for (final var t: terrArray) {
                    JSONObject tObj = (JSONObject) t;
                    String tName = tObj.get("name").toString();
                    this.territories.get(continentName).add(new TerritoryImpl(tName));
                }
            }
            for (final var elem: array) {
                obj = (JSONObject) elem;
                JSONArray terrArray = (JSONArray) obj.get("territories");
                for (final var t: terrArray) {
                    JSONObject tObj = (JSONObject) t;
                    String tName = tObj.get("name").toString();
                    JSONArray adjArray = (JSONArray) tObj.get("adj");
                    for (final var adjT: adjArray) {
                        this.getTerritory(tName).addAdjTerritory(this.getTerritory(adjT.toString()));
                    }
                }
            }
        } catch (IOException | ParseException e) {
            if (Files.notExists(Path.of(this.path), LinkOption.NOFOLLOW_LINKS)) {
                throw new FileNotFoundException();
            }
        }
    }

    @Override
    public String getContinentNameFromTerritory(final Territory t) {
        return this.territories.entrySet().stream().filter(x -> x.getValue().contains(t)).findFirst().get().getKey();
    }

    @Override
    public Map<String, Set<Territory>> getTerritoryMap() {
        return Map.copyOf(this.territories);
    }

    @Override
    public Set<String> getTerritoryNameSet() {
        return this.getTerritories().stream().map(t -> t.getName()).collect(Collectors.toSet());
    }

    @Override
    public Set<Territory> getTerritories() {
        Set<Territory> set = new HashSet<>();
        this.territories.values().stream().forEach(s -> set.addAll(s));
        return set;
    }

    @Override
    public Territory getTerritory(final String name) {
        return this.getTerritories().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().get();
    }

    @Override
    public Set<Territory> getTerritoryByContinent(final String name) {
        return this.getTerritoryMap().get(name);
    }

    @Override
    public String toString() {
        return this.getTerritories().toString();
    }
}
