package it.unibo.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.movement.api.Movement;
import it.unibo.model.movement.impl.MovementImpl;
import it.unibo.model.territory.api.GameTerritory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

public class TestMovement {

    private static int DEFAULT_TROOPS = 10;
    private static int MOVING_TROOPS = 4;
    private static int RETURNING_TROOPS = 10;

    private GameTerritory gameTerritory;

    @BeforeEach
    void startSetUp() {
        this.gameTerritory = new TerritoryFactoryImpl().createTerritories();
    }

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
        assertEquals(this.gameTerritory.getTerritory(t1).getTroops(), 6);
        assertEquals(this.gameTerritory.getTerritory(t2).getTroops(), 14);
        m2.moveTroops(RETURNING_TROOPS);
        assertEquals(this.gameTerritory.getTerritory(t1).getTroops(), 16);
        assertEquals(this.gameTerritory.getTerritory(t2).getTroops(), 4);
    }

}
