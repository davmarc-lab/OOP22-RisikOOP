package it.unibo.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.board.api.GameBoard;
import it.unibo.model.board.impl.GameBoardImpl;
import it.unibo.model.movement.api.Movement;
import it.unibo.model.movement.impl.MovementImpl;

/**
 * Tests the movement of troops between territories.
 */
class TestMovement {

    private static final int DEFAULT_TROOPS = 10;
    private static final int MOVING_TROOPS = 4;
    private static final int RETURNING_TROOPS = 10;
    private static final int INVALID_TROOPS = 100;
    private final GameBoard board = new GameBoardImpl();

    @Test
    void testCreationTerritories() {
        assertEquals(this.board.getGameTerritories().getTerritory("Alaska").getName(), "Alaska");
        assertEquals(this.board.getGameTerritories().getTerritory("Brazil").getName(), "Brazil");
        assertEquals(this.board.getGameTerritories().getTerritory("Mongolia").getName(), "Mongolia");
        assertEquals(this.board.getGameTerritories().getTerritory("Japan").getName(), "Japan");
    }

    @Test
    void testMovement() {
        final String t1 = "Japan";
        final String t2 = "Mongolia";
        this.board.getGameTerritories().getTerritory(t1).addTroops(DEFAULT_TROOPS);
        this.board.getGameTerritories().getTerritory(t2).addTroops(DEFAULT_TROOPS);
        final Movement m1 = new MovementImpl(this.board.getGameTerritories().getTerritory(t1),
                this.board.getGameTerritories().getTerritory(t2), MOVING_TROOPS);
        final Movement m2 = new MovementImpl(this.board.getGameTerritories().getTerritory(t2),
                this.board.getGameTerritories().getTerritory(t1), RETURNING_TROOPS);
        final Movement m3 = new MovementImpl(this.board.getGameTerritories().getTerritory(t1),
                this.board.getGameTerritories().getTerritory(t2), INVALID_TROOPS);
        final Movement m4 = new MovementImpl(this.board.getGameTerritories().getTerritory(t2),
                this.board.getGameTerritories().getTerritory(t1), INVALID_TROOPS);
        assertTrue(m1.isMovementValid());
        assertTrue(m2.isMovementValid());
        assertFalse(m3.isMovementValid());
        assertFalse(m4.isMovementValid());
    }

}
