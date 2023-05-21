package it.unibo.territory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.api.TerritoryFactory;
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

    private TerritoryFactory factory;

    /**
     * Creates all the territories using the factory.
     */
    @BeforeEach
    void initTerritoryFactory() {
        this.factory = new TerritoryFactoryImpl();
        this.factory.createTerritories();
    }

    /**
     * Test the creation of the territories.
     */
    @Test
    void testCreationTerritories() {
        assertDoesNotThrow(() -> new TerritoryFactoryImpl().createTerritories());
        final Territory t = this.factory.getTerritory("Argentina");
        assertEquals(t.getName(), "Argentina");
        assertEquals(t.getAdjTerritories().stream().map(e -> e.getName()).sorted().collect(Collectors.toSet()),
                Set.of("Brazil", "Peru'"));
        assertThrows(NoSuchElementException.class, () -> this.factory.getTerritory("Italy"));
    }

    /**
     * Test the continent of a territory.
     */
    @Test
    void testContinentFromTerritory() {
        final Territory tM = this.factory.getTerritory("Madagascar");
        final Territory tJ = this.factory.getTerritory("Japan");
        assertEquals(this.factory.getContinentNameFromTerritory(tM), "Africa");
        assertNotEquals(this.factory.getContinentNameFromTerritory(tJ), "Europe");
        assertThrows(NoSuchElementException.class,
                () -> this.factory.getContinentNameFromTerritory(new TerritoryImpl("France")));
    }

    /**
     * Test the set of all territory names.
     */
    @Test
    void testTerritoryNameSet() {
        final Set<String> nameSet = this.factory.getTerritoryNameSet();
        assertEquals(nameSet.stream().filter(s -> "alaska".equalsIgnoreCase(s)).findAny().get(), "Alaska");
        assertTrue(nameSet.contains("China"));
        assertFalse(nameSet.contains("japan")); // Not ignoring name's first letter upper case.
    }

    /**
     * Test the adjacency of territories.
     */
    @Test
    void testAdjTerritories() {
        final Set<String> set = new HashSet<>();
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

    /**
     * Test the set of all territories.
     */
    @Test
    void testTerritorySet() {
        final Set<Territory> territories = this.factory.getTerritories();
        assertInstanceOf(Territory.class,
                territories.stream().filter(t -> "Quebec".equalsIgnoreCase(t.getName())).findAny().get());
        assertTrue(territories.contains(this.factory.getTerritory("Ontario")));
        assertFalse(territories.contains(new TerritoryImpl("Alberia")));
    }

    /**
     * Test the set of territories of a continent.
     */
    @Test
    void testTerritoryByContinent() {
        assertTrue(this.factory.getTerritoryByContinent("Europe").contains(this.factory.getTerritory("Great Britain")));
        assertTrue(this.factory.getTerritoryByContinent("Oceania").contains(this.factory.getTerritory("Indonesia")));
        assertEquals(
                this.factory.getTerritoryByContinent("North America").stream()
                        .filter(t -> "Alaska".equalsIgnoreCase(t.getName())).findAny().get(),
                this.factory.getTerritory("Alaska"));
    }
}
