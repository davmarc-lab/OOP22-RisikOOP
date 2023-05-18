package it.unibo.combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    private static final List<Integer> ATTACKERS_INTEGERS = new ArrayList<>(List.of(6, 5));
    private static final List<Integer> DEFENDERS_INTEGERS = new ArrayList<>(List.of(5, 2, 1));

    private final Player p1 = new PlayerImpl(1);
    private final Player p2 = new PlayerImpl(2);

    private TerritoryFactory factory;

    @BeforeEach void startSetUp() {
        this.factory = new TerritoryFactoryImpl();
        this.factory.createTerritories();
        p1.removeTerritory(p1.getTerritories().stream());
        p2.removeTerritory(p2.getTerritories().stream());

        p1.addTerritory(Stream.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela"),
            factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")));
        p2.addTerritory(Stream.of(factory.getTerritory("Brazil"), factory.getTerritory("Ukraine")));

    }

    @Test
    void testCreationTerritories() {
        assertEquals(this.factory.getTerritory("Alaska").getName(), "Alaska");
    }

    @Test
    public void addFirstTerritoryTest() {
        assertEquals(Set.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela"),
            factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")), p1.getTerritories());
        assertEquals(Set.of(factory.getTerritory("Brazil"), factory.getTerritory("Ukraine")), p2.getTerritories());
    }

    @Test
    public void removeTerritoriesTest() {
        p1.removeTerritory(Stream.of(factory.getTerritory("Egypt"), factory.getTerritory("Scandinavia")));
        assertEquals(Set.of(factory.getTerritory("Southern Europe"), factory.getTerritory("Venezuela")), p1.getTerritories());
        p2.removeTerritory(Stream.of(factory.getTerritory("Ukraine")));
        assertEquals(Set.of(factory.getTerritory("Brazil")), p2.getTerritories());
        p2.addTerritory(Stream.of(factory.getTerritory("Egypt"), factory.getTerritory("Ukraine")));
        assertEquals(Set.of(factory.getTerritory("Brazil"), factory.getTerritory("Egypt"),
            factory.getTerritory("Ukraine")), p2.getTerritories());

    }

    @Test
    public void combatTestWithForcedResults() {
        var s = factory.getTerritory("Southern Europe");
        s.addArmy(2);
        var d = factory.getTerritory("Ukraine");
        d.addArmy(3);
        Combat c1 = new CombatImpl(s, 2, d, 3, new ArrayList<>(ATTACKERS_INTEGERS), new ArrayList<>(DEFENDERS_INTEGERS));
        assertEquals(List.of(Combat.Results.WIN, Combat.Results.WIN), c1.attack());
        assertEquals(List.of(2, 1), List.of(s.getArmy(), d.getArmy()));
    }

    @Test
    public void throwingExceptionForNumberStrikerNotValid() {
        var s = factory.getTerritory("Southern Europe");
        var d = factory.getTerritory("Ukraine");
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatImpl(s, 0, d, 3).attack();
        });
    }
}
