package it.unibo.combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.deck.impl.DeckImpl;
import it.unibo.model.hand.impl.HandImpl;
import it.unibo.model.objective.impl.ObjectiveBuilderImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerBuilderImpl;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

import java.util.List;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Tests the combat between two territories.
 */
class TestCombat {

    private static final List<Integer> ATTACKERS_INTEGERS = new ArrayList<>(List.of(6, 5));
    private static final List<Integer> DEFENDERS_INTEGERS = new ArrayList<>(List.of(5, 2, 1));
    private static final String SOUTHERN_EUROPE_NAME = "Southern Europe";
    private static final String EGYPT_NAME = "Egypt";
    private static final String BRAZIL_NAME = "Brazil";
    private static final String UKRAINE_NAME = "Ukraine";
    private static final String VENEZUELA_NAME = "Venezuela";
    private static final String SCANDINAVIA_NAME = "Scandinavia";

    private final Player p1 = PlayerBuilderImpl.newBuilder().id(1).territoryDeck(new DeckImpl<>())
            .playerHand(new HandImpl()).objective(ObjectiveBuilderImpl.newBuilder().build()).build();
    private final Player p2 = PlayerBuilderImpl.newBuilder().id(2).territoryDeck(new DeckImpl<>())
            .playerHand(new HandImpl()).objective(ObjectiveBuilderImpl.newBuilder().build()).build();

    private GameTerritory territories;

    @BeforeEach
    void startSetUp() {
        this.territories = new TerritoryFactoryImpl().createTerritories();

        Stream.of(territories.getTerritory(SOUTHERN_EUROPE_NAME), territories.getTerritory(VENEZUELA_NAME),
                territories.getTerritory(EGYPT_NAME), territories.getTerritory(SCANDINAVIA_NAME))
                .forEach(t -> p1.addTerritory(t));
        Stream.of(territories.getTerritory(BRAZIL_NAME), territories.getTerritory(UKRAINE_NAME))
                .forEach(t -> p2.addTerritory(t));
    }

    @Test
    void testCreationTerritories() {
        assertEquals(this.territories.getTerritory("Alaska").getName(), "Alaska");
    }

    @Test
    void addFirstTerritoryTest() {
        assertEquals(Set.of(territories.getTerritory(SOUTHERN_EUROPE_NAME), territories.getTerritory(VENEZUELA_NAME),
                territories.getTerritory(EGYPT_NAME), territories.getTerritory(SCANDINAVIA_NAME)), p1.getTerritories());
        assertEquals(Set.of(territories.getTerritory(BRAZIL_NAME), territories.getTerritory(UKRAINE_NAME)),
                p2.getTerritories());
    }

    @Test
    void removeTerritoriesTest() {
        Stream.of(territories.getTerritory(EGYPT_NAME), territories.getTerritory(SCANDINAVIA_NAME))
                .forEach(t -> p1.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory(SOUTHERN_EUROPE_NAME), territories.getTerritory(VENEZUELA_NAME)),
                p1.getTerritories());
        Stream.of(territories.getTerritory(UKRAINE_NAME)).forEach(t -> p2.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory(BRAZIL_NAME)), p2.getTerritories());
        Stream.of(territories.getTerritory(EGYPT_NAME), territories.getTerritory(UKRAINE_NAME))
                .forEach(t -> p2.addTerritory(t));
        assertEquals(Set.of(territories.getTerritory(BRAZIL_NAME), territories.getTerritory(EGYPT_NAME),
                territories.getTerritory(UKRAINE_NAME)), p2.getTerritories());
    }

    @Test
    void combatTestWithForcedResults() {
        final var s = territories.getTerritory(SOUTHERN_EUROPE_NAME);
        s.addTroops(2);
        final var d = territories.getTerritory(UKRAINE_NAME);
        d.addTroops(3);
        final Combat c1 = new CombatImpl(s, 2, d, 3, new ArrayList<>(ATTACKERS_INTEGERS), new ArrayList<>(DEFENDERS_INTEGERS),
                true);
        assertEquals(List.of(Combat.Result.WIN, Combat.Result.WIN), c1.attack(2, 3));
        d.addTroops(-(int) List.of(Combat.Result.WIN, Combat.Result.WIN).stream()
                .filter(r -> r.equals(Combat.Result.WIN)).count());
        assertEquals(List.of(2, 1), List.of(s.getTroops(), d.getTroops()));
    }

    @Test
    void throwingExceptionForNumberAttackerNotValid() {
        final var s = territories.getTerritory(SOUTHERN_EUROPE_NAME);
        final var d = territories.getTerritory(UKRAINE_NAME);
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatImpl(s, 0, d, 3, true).attack(0, 3);
        });
    }
}
