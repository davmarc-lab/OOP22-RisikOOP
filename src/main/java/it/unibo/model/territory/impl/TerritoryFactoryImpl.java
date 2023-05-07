package it.unibo.model.territory.impl;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.*;
import org.json.simple.parser.*;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;

public class TerritoryFactoryImpl implements TerritoryFactory {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR
            + "config" + PATH_SEPARATOR + "territory" + PATH_SEPARATOR + "Territories.json";

    private Set<Territory> territories;

    public TerritoryFactoryImpl() {
        this.territories = new HashSet<>();
    }

    @Override
    public void createTerritorySet() {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            JSONArray array = (JSONArray)parser.parse(new FileReader(PATH));
            for (final Object elem: array) {
                obj = (JSONObject)elem;
                String name = obj.get("name").toString();
                JSONArray adjArray = (JSONArray)obj.get("adj");
                if (!this.getNameSet().contains(name)) {
                    this.territories.add(new TerritoryImpl(name));
                }
                Territory t = this.getTerritory(name);
                for (final Object array_elem: adjArray) {
                    if (!this.getNameSet().contains(array_elem.toString())) {
                        this.territories.add(new TerritoryImpl(array_elem.toString()));
                    }
                    t.addAdjTerritory(this.getTerritory(array_elem.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Territory> getTerritories() {
        return this.territories;
    }

    @Override
    public Set<String> getNameSet() {
        return this.territories.stream()
                .map(t -> t.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Territory getTerritory(final String name) {
        return this.territories.stream()
                .filter(t -> t.getName().compareToIgnoreCase(name) == 0)
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        return new String(new StringBuilder("{")
                .append(this.getTerritories().stream()
                        .map(t -> t.toString())
                        .reduce((s1, s2) -> s1 + "; " + s2)
                        .get())
                .append("}"));
    }
}
