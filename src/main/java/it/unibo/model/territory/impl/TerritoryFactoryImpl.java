package it.unibo.model.territory.impl;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.*;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;

public class TerritoryFactoryImpl implements TerritoryFactory {

    private Set<Territory> territories;

    public TerritoryFactoryImpl() {
        this.territories = new HashSet<>();
    }

    @Override
    public void createTerritorySet() {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {
            JSONArray array = (JSONArray)parser.parse(new FileReader("src/main/resources/config/territory/Territories.json"));
            for (final Object elem: array) {
                obj = (JSONObject)elem;
                this.territories.add(new TerritoryImpl(obj.get("name").toString()));
            }
            for (final Object elem: array) {
                obj = (JSONObject)elem;
                this.createAdjTerritories(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAdjTerritories(final JSONObject obj) {
        Territory t = this.getTerritory(obj.get("name").toString());
        JSONArray adjArray = (JSONArray)obj.get("adj");

        adjArray.stream().forEach((e -> t.addAdjTerritory(this.getTerritory(e.toString()))));
    }

    @Override
    public Set<Territory> getTerritories() {
        return this.territories;
    }

    @Override
    public Set<String> getNameSet() {
        Set<String> nameSet = new HashSet<>();
        this.territories.forEach(t -> nameSet.add(t.getName()));
        return nameSet;
    }

    @Override
    public Territory getTerritory(final String name) {
        return this.territories.stream().filter(t -> t.getName().compareToIgnoreCase(name) == 0).findFirst().get();
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("{");
        this.getTerritories().forEach(t -> sBuilder.append(t).append("; "));
        sBuilder.deleteCharAt(sBuilder.length() - 2);
        sBuilder.append("}");

        return sBuilder.toString();
    }
}
