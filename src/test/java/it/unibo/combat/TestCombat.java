package it.unibo.combat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

class TestCombat {

    final Player p1 = new PlayerImpl(1);
    final Player p2 = new PlayerImpl(2);

    private TerritoryFactory factory;

    public void defaultSetUp() {
        this.factory = new TerritoryFactoryImpl();
        this.factory.createTerritorySet();
        p1.removeTerritory(p1.getTerritories().stream());
        p2.removeTerritory(p2.getTerritories().stream());
    }

    private void assignDefaultTerritories() {
        defaultSetUp();
        p1.addTerritory(Stream.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela"),
            factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")));
        p2.addTerritory(Stream.of(factory.getTerritory("Brazil"), factory.getTerritory("Ukraine")));
    }

    @Test
    public void attempSetUpTest() {
        assertEquals(0, p1.getTerritories().size());
        assertEquals(0, p2.getTerritories().size());
    }

    @Test
    void testCreationTerritories() {
        defaultSetUp();
        assertEquals(this.factory.getTerritory("Alaska").getName(), "Alaska");
    }

    @Test
    public void addFirstTerritoryTest() {
        assignDefaultTerritories();
        assertEquals(Set.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela"),
            factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")), p1.getTerritories());
        assertEquals(Set.of(factory.getTerritory("Brazil"), factory.getTerritory("Ukraine")), p2.getTerritories());
    }

    @Test
    public void removeTerritoriesTest() {
        assignDefaultTerritories();
        p1.removeTerritory(Stream.of(factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")));
        assertEquals(Set.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela")), p1.getTerritories());
        p2.removeTerritory(Stream.of(factory.getTerritory("Ukraine")));
        assertEquals(Set.of(factory.getTerritory("Brazil")), p2.getTerritories());
        p2.addTerritory(Stream.of(factory.getTerritory("Egypt"), factory.getTerritory("Ukraine")));
        assertEquals(Set.of(factory.getTerritory("Brazil"), factory.getTerritory("Egypt"), factory.getTerritory("Ukraine")), p2.getTerritories());

    }

    @Test
    public void combatTestWithForcedResults() {
        assignDefaultTerritories();
        var s = factory.getTerritory("Southern Europe");
        var d = factory.getTerritory("Ukraine");
        Combat c1 = new CombatImpl(s, 2, d, 3, new ArrayList<>(List.of(6, 4)), new ArrayList<>(List.of(5, 2, 1)));
        assertEquals(List.of(Combat.Results.WIN, Combat.Results.WIN), c1.attack());
    }

    @Test
    public void throwingExceptionForNumberStrikerNotValid() {
        assignDefaultTerritories();
        var s = factory.getTerritory("Southern Europe");
        var d = factory.getTerritory("Ukraine");
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatImpl(s, 0, d, 3).attack();
        });
    }
}
