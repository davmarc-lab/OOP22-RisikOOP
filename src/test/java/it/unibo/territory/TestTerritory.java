package it.unibo.territory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;
import it.unibo.model.territory.impl.TerritoryImpl;

public class TestTerritory {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH = "src" + PATH_SEPARATOR + "main" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR
            + "config" + PATH_SEPARATOR + "territory" + PATH_SEPARATOR + "Territories.json";

    private TerritoryFactory factory;
    private JSONParser parser = new JSONParser();

    @BeforeEach
    public void initTerritoryFactory() {
        System.out.println(PATH);
        this.factory = new TerritoryFactoryImpl();
        this.factory.createTerritorySet();
    }

    @Test
    public void testCreationTerritories() {
        assertEquals(this.factory.getTerritory("Alaska").getName(), "Alaska");
        assertEquals(this.factory.getNameSet(), nameSetCreationToTest());
        assertTrue(this.factory.getTerritories().contains(this.factory.getTerritory("mongolia"))); //Ignore upper and lower case
        assertFalse(this.factory.getTerritories().contains(new TerritoryImpl("Greenlands"))); //Wrong name
    }

    private Set<String> nameSetCreationToTest() {
        Set<String> set = new HashSet<>();
        try {
            JSONArray array = (JSONArray)this.parser.parse(new FileReader(PATH));
            JSONObject obj = new JSONObject();
            for (final Object elem: array) {
                obj = (JSONObject)elem;
                set.add(obj.get("name").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    @Test
    public void testAdjTerritories() {
        Set<String> set = new HashSet<>();
        this.factory.getTerritory("Southern Europe").getAdjTerritories().forEach(t -> set.add(t.getName()));
        assertTrue(set.containsAll(Set.of(
            "Western Europe",
            "Northern Europe",
            "Ukraine",
            "North Africa",
            "Egypt",
            "Middle East")));
        set.clear();
        this.factory.getTerritory("Eastern Australia").getAdjTerritories().forEach(t -> set.add(t.getName()));
        assertTrue(set.containsAll(Set.of("Western Australia", "New Guinea")));
        assertFalse(this.factory.getTerritory("Japan").getAdjTerritories().contains(this.factory.getTerritory("Siam")));
    }
}
