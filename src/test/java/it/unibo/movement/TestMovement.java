package it.unibo.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.movement.impl.MovementImpl;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

/**
 * Tests the movement of troops between territories.
 */
public class TestMovement {

    private static final int DEFAULT_TROOPS = 10;
    private static final int MOVING_TROOPS = 4;
    private static final int RETURNING_TROOPS = 10;

    private GameTerritory gameTerritory = new TerritoryFactoryImpl().createTerritories();

    @Test
    void testCreationTerritories() {
        assertEquals(this.gameTerritory.getTerritory("Alaska").getName(), "Alaska");
        assertEquals(this.gameTerritory.getTerritory("Brazil").getName(), "Brazil");
        assertEquals(this.gameTerritory.getTerritory("Scandinavia").getName(), "Scandinavia");
        assertEquals(this.gameTerritory.getTerritory("Japan").getName(), "Japan");
    }

    @Test
    void testMovement() {
        String t1 = "Japan";
        String t2 = "Alaska";
        this.gameTerritory.getTerritory(t1).addTroops(DEFAULT_TROOPS);
        this.gameTerritory.getTerritory(t2).addTroops(DEFAULT_TROOPS);
        Movement m1 = new MovementImpl(this.gameTerritory.getTerritory(t1), this.gameTerritory.getTerritory(t2));
        Movement m2 = new MovementImpl(this.gameTerritory.getTerritory(t2), this.gameTerritory.getTerritory(t1));
        m1.moveTroops(MOVING_TROOPS);
        final List<Integer> results = List.of(6, 14, 16, 4);
        assertEquals(this.gameTerritory.getTerritory(t1).getTroops(), results.get(0));
        assertEquals(this.gameTerritory.getTerritory(t2).getTroops(), results.get(1));
        m2.moveTroops(RETURNING_TROOPS);
        assertEquals(this.gameTerritory.getTerritory(t1).getTroops(), results.get(2));
        assertEquals(this.gameTerritory.getTerritory(t2).getTroops(), results.get(3));
    }

}
