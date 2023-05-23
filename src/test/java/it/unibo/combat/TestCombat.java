package it.unibo.combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.api.GameTerritory;
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

    private GameTerritory territories;

    @BeforeEach void startSetUp() {
        this.territories = new TerritoryFactoryImpl().createTerritories();

        Stream.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela"),
            territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia")).forEach(t -> p1.addTerritory(t));
        Stream.of(territories.getTerritory("Brazil"), territories.getTerritory("Ukraine")).forEach(t -> p2.addTerritory(t));

    }

    @Test
    void testCreationTerritories() {
        assertEquals(this.territories.getTerritory("Alaska").getName(), "Alaska");
    }

    @Test
    public void addFirstTerritoryTest() {
        assertEquals(Set.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela"),
            territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia")), p1.getTerritories());
        assertEquals(Set.of(territories.getTerritory("Brazil"), territories.getTerritory("Ukraine")), p2.getTerritories());
    }

    @Test
    public void removeTerritoriesTest() {
        Stream.of(territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia")).forEach(t -> p1.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela")), p1.getTerritories());
        Stream.of(territories.getTerritory("Ukraine")).forEach(t -> p2.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Brazil")), p2.getTerritories());
        Stream.of(territories.getTerritory("Egypt"), territories.getTerritory("Ukraine")).forEach(t -> p2.addTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Brazil"), territories.getTerritory("Egypt"),
            territories.getTerritory("Ukraine")), p2.getTerritories());

    }

    @Test
    public void combatTestWithForcedResults() {
        var s = territories.getTerritory("Southern Europe");
        s.addTroops(2);
        var d = territories.getTerritory("Ukraine");
        d.addTroops(3);
        Combat c1 = new CombatImpl(s, 2, d, 3, new ArrayList<>(ATTACKERS_INTEGERS), new ArrayList<>(DEFENDERS_INTEGERS), true);
        assertEquals(List.of(Combat.Results.WIN, Combat.Results.WIN), c1.attack(2, 3));
        assertEquals(List.of(2, 1), List.of(s.getTroops(), d.getTroops()));
    }

    @Test
    public void throwingExceptionForNumberStrikerNotValid() {
        var s = territories.getTerritory("Southern Europe");
        var d = territories.getTerritory("Ukraine");
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatImpl(s, 0, d, 3).attack(0, 3);
        });
    }
}
