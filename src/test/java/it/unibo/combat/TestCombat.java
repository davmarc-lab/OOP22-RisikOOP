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

class TestCombat {

    private static final List<Integer> ATTACKERS_INTEGERS = new ArrayList<>(List.of(6, 5));
    private static final List<Integer> DEFENDERS_INTEGERS = new ArrayList<>(List.of(5, 2, 1));

    private final Player p1 = PlayerBuilderImpl.newBuilder().id(1).territoryDeck(new DeckImpl<>())
            .playerHand(new HandImpl()).objective(ObjectiveBuilderImpl.newBuilder().build()).build();
    private final Player p2 = PlayerBuilderImpl.newBuilder().id(2).territoryDeck(new DeckImpl<>())
            .playerHand(new HandImpl()).objective(ObjectiveBuilderImpl.newBuilder().build()).build();

    private GameTerritory territories;

    @BeforeEach
    void startSetUp() {
        this.territories = new TerritoryFactoryImpl().createTerritories();

        Stream.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela"),
                territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia"))
                .forEach(t -> p1.addTerritory(t));
        Stream.of(territories.getTerritory("Brazil"), territories.getTerritory("Ukraine"))
                .forEach(t -> p2.addTerritory(t));

    }

    @Test
    void testCreationTerritories() {
        assertEquals(this.territories.getTerritory("Alaska").getName(), "Alaska");
    }

    @Test
    void addFirstTerritoryTest() {
        assertEquals(Set.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela"),
                territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia")), p1.getTerritories());
        assertEquals(Set.of(territories.getTerritory("Brazil"), territories.getTerritory("Ukraine")),
                p2.getTerritories());
    }

    @Test
    void removeTerritoriesTest() {
        Stream.of(territories.getTerritory("Egypt"), territories.getTerritory("Scandinavia"))
                .forEach(t -> p1.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Southern Europe"), territories.getTerritory("Venezuela")),
                p1.getTerritories());
        Stream.of(territories.getTerritory("Ukraine")).forEach(t -> p2.removeTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Brazil")), p2.getTerritories());
        Stream.of(territories.getTerritory("Egypt"), territories.getTerritory("Ukraine"))
                .forEach(t -> p2.addTerritory(t));
        assertEquals(Set.of(territories.getTerritory("Brazil"), territories.getTerritory("Egypt"),
                territories.getTerritory("Ukraine")), p2.getTerritories());

    }

    @Test
    void combatTestWithForcedResults() {
        final var s = territories.getTerritory("Southern Europe");
        s.addTroops(2);
        final var d = territories.getTerritory("Ukraine");
        d.addTroops(3);
        final Combat c1 = new CombatImpl(s, 2, d, 3, new ArrayList<>(ATTACKERS_INTEGERS), new ArrayList<>(DEFENDERS_INTEGERS),
                true);
        assertEquals(List.of(Combat.Result.WIN, Combat.Result.WIN), c1.attack(2, 3));
        assertEquals(List.of(2, 1), List.of(s.getTroops(), d.getTroops()));
    }

    @Test
    void throwingExceptionForNumberAttackerNotValid() {
        final var s = territories.getTerritory("Southern Europe");
        final var d = territories.getTerritory("Ukraine");
        assertThrows(IllegalArgumentException.class, () -> {
            new CombatImpl(s, 0, d, 3).attack(0, 3);
        });
    }
}
