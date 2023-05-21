package it.unibo.controller;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
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

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryImpl;
import it.unibo.common.Pair;

public class JsonReaderTerritory extends AbstractJsonReader<List<Pair<String, Set<Territory>>>> {

    private static final String PATH = "territory";
    private static final String FILE_NAME = "Territories.json";

    private List<Pair<String, Set<Territory>>> territories;

    public JsonReaderTerritory() {
        super(PATH, FILE_NAME);
        this.territories = new ArrayList<>();
    }

    @Override
    public List<Pair<String, Set<Territory>>> readFromJSON() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;

        try {
            final FileInputStream fileInputStream = new FileInputStream(this.getFilePath());
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final String continentName = obj.get("continent").toString();
                final JSONArray terrArray = (JSONArray) obj.get("territories");
                this.territories.add(new Pair<>(continentName, new HashSet<>()));
                for (final var t : terrArray) {
                    final JSONObject tObj = (JSONObject) t;
                    final String tName = tObj.get("name").toString();
                    this.territories.stream()
                            .filter(x -> continentName.equals(x.getX()))
                            .findAny().get().getY()
                            .add(new TerritoryImpl(tName));
                }
            }
            for (final var elem : array) {
                obj = (JSONObject) elem;
                final JSONArray terrArray = (JSONArray) obj.get("territories");
                for (final var t : terrArray) {
                    final JSONObject tObj = (JSONObject) t;
                    final String tName = tObj.get("name").toString();
                    final JSONArray adjArray = (JSONArray) tObj.get("adj");
                    for (final var adjT : adjArray) {
                        this.getTerritory(tName).addAdjTerritory(this.getTerritory(adjT.toString()));
                    }
                }
            }
            fileInputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "File not found in the path", e);
        } catch (ParseException e1) {
            this.getLogger().log(Level.SEVERE, "Excpetion in parsing the file", e1);
        }
        return List.copyOf(this.territories);
    }

    private Territory getTerritory(final String name) {
        return this.getTerritories().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findAny().get();
    }

    private Set<Territory> getTerritories() {
        final Set<Territory> set = new HashSet<>();
        this.territories.stream().forEach(p -> set.addAll(p.getY()));
        return set;
    }
}
