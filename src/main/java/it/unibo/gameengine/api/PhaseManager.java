package it.unibo.gameengine.api;

/**
 * Used to manage all the phases of a turn.
 */
public interface PhaseManager {

    /**
     * Determine the phase of a turn.
     */
    enum Phase {
        /**
         * The player is given troops based on the territories he owns.
         */
        REINFORCEMENTS,

        /**
         * The player can attack another player's territory to conquer it.
         */
        COMBAT,

        /**
         * The player can move some of his troops from one of his territories to
         * another.
         */
        MOVEMENT
    }

    /**
     * Retrieves the current phase of the turn.
     * 
     * @return the current phase of the turn
     */
    Phase getCurrentPhase();

    /**
     * Switches to the next phase.
     */
    void switchToNextPhase();

    /**
     * Switches to the specified phase.
     * 
     * @param phase the phase
     */
    void switchToPhase(Phase phase);
}
