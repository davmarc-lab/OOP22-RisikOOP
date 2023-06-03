package it.unibo.objective;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.objective.api.GameObjective;
import it.unibo.model.objective.api.Objective;
import it.unibo.model.objective.impl.ObjectiveFactoryImpl;

/**
 * Test class for Objective.
 */
class TestObjective {

    private GameObjective objectives;
    private static final int NUMBER_OBJECTIVES = 14;
    private static final int NUMBER_CONQUER_OBJECTIVES = 8;
    private static final int NUMBER_DESTROY_OBJECTIVES = 6;

    @BeforeEach
    void initObjectiveFactory() {
        this.objectives = new ObjectiveFactoryImpl().createObjectiveSet();
    }

    @Test
    void testCreationTerritories() {
        assertDoesNotThrow(() -> new ObjectiveFactoryImpl().createObjectiveSet());
    }

    @Test
    void testSetObjectives() {
        final Set<Objective> setObjectives = this.objectives.getSetObjectives();
        assertEquals(setObjectives.size(), NUMBER_OBJECTIVES);
        assertEquals(setObjectives.stream()
                .filter(obj -> obj.getObjectiveType().equals(Objective.ObjectiveType.CONQUER)).count(),
                NUMBER_CONQUER_OBJECTIVES);
        assertEquals(setObjectives.stream()
                .filter(obj -> obj.getObjectiveType().equals(Objective.ObjectiveType.DESTROY)).count(),
                NUMBER_DESTROY_OBJECTIVES);
    }

    @Test
    void testDefaultObjective() {
        final Objective defaulObjective = this.objectives.getDefaultObjective();
        assertEquals(defaulObjective.getDescription(), "Conquer 24 territories with at least 1 troop");
        assertEquals(defaulObjective.getObjectiveType(), Objective.ObjectiveType.CONQUER);
        assertEquals(defaulObjective.getCheckObjectives().getY().get(0), "24");
        assertEquals(defaulObjective.getCheckObjectives().getY().get(1), "1");
        assertEquals(defaulObjective.getCheckObjectives().getY().size(), 2);
    }

    @Test
    void testConquerObjective() {
        final Objective conquerObjective = this.objectives.getSetObjectives().stream()
                .filter(obj -> "Conquer Asia and Africa".equals(obj.getDescription())).findFirst().get();
        assertEquals(conquerObjective.getObjectiveType(), Objective.ObjectiveType.CONQUER);
        assertEquals(conquerObjective.getCheckObjectives().getY().get(0), "Asia");
        assertEquals(conquerObjective.getCheckObjectives().getY().get(1), "Africa");
        assertEquals(conquerObjective.getCheckObjectives().getY().get(2), "false");
        assertEquals(conquerObjective.getCheckObjectives().getY().size(), 3);
        final Objective conquerObjective2 = this.objectives.getSetObjectives().stream()
                .filter(obj -> "Conquer Europe and Oceania and another continent of your choice"
                        .equals(obj.getDescription()))
                .findFirst().get();
        assertEquals(conquerObjective2.getObjectiveType(), Objective.ObjectiveType.CONQUER);
        assertEquals(conquerObjective2.getCheckObjectives().getY().get(0), "Europe");
        assertEquals(conquerObjective2.getCheckObjectives().getY().get(1), "Oceania");
        assertEquals(conquerObjective2.getCheckObjectives().getY().get(2), "true");
        assertEquals(conquerObjective2.getCheckObjectives().getY().size(), 3);
    }

    @Test
    void testDestroyObjective() {
        final Objective destroyObjective = this.objectives.getSetObjectives().stream()
                .filter(obj -> "Destroy the RED army".equals(obj.getDescription())).findFirst().get();
        assertEquals(destroyObjective.getObjectiveType(), Objective.ObjectiveType.DESTROY);
        assertEquals(destroyObjective.getCheckObjectives().getY().get(0), "RED");
        assertEquals(destroyObjective.getCheckObjectives().getY().size(), 1);
    }

    @Test
    void testConquerNumberObjective() {
        final Objective conquerNumberObjective = this.objectives.getSetObjectives().stream()
                .filter(obj -> "Conquer 18 territories with at least 2 troops".equals(obj.getDescription())).findFirst()
                .get();
        assertEquals(conquerNumberObjective.getObjectiveType(), Objective.ObjectiveType.CONQUER);
        assertEquals(conquerNumberObjective.getCheckObjectives().getY().get(0), "18");
        assertEquals(conquerNumberObjective.getCheckObjectives().getY().get(1), "2");
        assertEquals(conquerNumberObjective.getCheckObjectives().getY().size(), 2);
    }

}
