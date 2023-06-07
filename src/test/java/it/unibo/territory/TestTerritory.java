package it.unibo.territory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;
import it.unibo.model.territory.impl.TerritoryImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Test the creation and the contents of territories.
 */
class TestTerritory {

    private GameTerritory territories;

    /**
     * Creates all the territories using the factory.
     */
    @BeforeEach
    void initTerritoryFactory() {
        this.territories = new TerritoryFactoryImpl().createTerritories();
    }

    /**
     * Test the creation of the territories.
     */
    @Test
    void testCreationTerritories() {
        assertDoesNotThrow(() -> new TerritoryFactoryImpl().createTerritories());
        final Territory t = this.territories.getTerritory("Argentina");
        assertEquals(t.getName(), "Argentina");
        assertEquals(t.getAdjTerritories().stream()
                .map(e -> e.getName())
                .sorted()
                .collect(Collectors.toSet()), Set.of("Brazil", "Peru'"));
        assertThrows(NoSuchElementException.class, () -> this.territories.getTerritory("Italy"));
    }

    /**
     * Test the continent of a territory.
     */
    @Test
    void testContinentFromTerritory() {
        final Territory tM = this.territories.getTerritory("Madagascar");
        final Territory tJ = this.territories.getTerritory("Japan");
        assertEquals(this.territories.getContinentNameFromTerritory(tM), "Africa");
        assertNotEquals(this.territories.getContinentNameFromTerritory(tJ), "Europe");
        assertThrows(NoSuchElementException.class,
                () -> this.territories.getContinentNameFromTerritory(new TerritoryImpl("France")));
    }

    /**
     * Test the set of all territory names.
     */
    @Test
    void testTerritoryNameSet() {
        final Set<String> nameSet = this.territories.getTerritoryNameSet();
        assertEquals(nameSet.stream()
                .filter(s -> "alaska".equalsIgnoreCase(s))
                .findAny()
                .get(), "Alaska");
        assertTrue(nameSet.contains("China"));
        assertFalse(nameSet.contains("japan")); // Not ignoring name's first letter upper case.
    }

    /**
     * Test the adjacency of territories.
     */
    @Test
    void testAdjTerritories() {
        final Set<String> set = new HashSet<>();
        this.territories.getTerritory("Southern Europe").getAdjTerritories().forEach(t -> set.add(t.getName()));
        assertTrue(set.containsAll(Set.of(
                "Western Europe",
                "Northern Europe",
                "Ukraine",
                "North Africa",
                "Egypt",
                "Middle East")));
        set.clear();
        this.territories.getTerritory("Eastern Australia")
                .getAdjTerritories()
                .forEach(t -> set.add(t.getName()));
        assertTrue(set.containsAll(Set.of("Western Australia", "New Guinea")));
        assertFalse(this.territories.getTerritory("Japan")
                .getAdjTerritories()
                .contains(this.territories.getTerritory("Siam")));
    }

    /**
     * Test the set of all territories.
     */
    @Test
    void testTerritorySet() {
        final Set<Territory> territories = this.territories.getTerritories();
        assertInstanceOf(Territory.class, territories.stream()
                .filter(t -> "Quebec".equalsIgnoreCase(t.getName()))
                .findAny()
                .get());
        assertTrue(territories.contains(this.territories.getTerritory("Ontario")));
        assertFalse(territories.contains(new TerritoryImpl("Alberia")));
    }

    /**
     * Test the set of territories of a continent.
     */
    @Test
    void testTerritoryByContinent() {
        assertTrue(this.territories.getTerritoryByContinent("Europe").contains(this.territories.getTerritory("Great Britain")));
        assertTrue(this.territories.getTerritoryByContinent("Oceania").contains(this.territories.getTerritory("Indonesia")));
        assertEquals(this.territories.getTerritoryByContinent("North America").stream()
                .filter(t -> "Alaska".equalsIgnoreCase(t.getName()))
                .findAny()
                .get(), this.territories.getTerritory("Alaska"));
    }
}
