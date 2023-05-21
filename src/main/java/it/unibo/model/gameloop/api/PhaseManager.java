package it.unibo.model.gameloop.api;

/**
 * This class is used to manage all the phases of a turn.
 */
public interface PhaseManager {

    /**
     * This enum is used to determine the phase of a turn.
     */
    enum Phase {
        /**
         * The player is given troops based on the territories he owns.
         */
        PREPARATION,

        /**
         * The player can play his cards to gain bonus troops.
         */
        PLAY_CARDS,

        /**
         * The player can attack another player's territory to conquer it.
         */
        COMBAT,

        /**
         * The player can move some of his troops from one of his territories to
         * another.
         */
        MOVEMENT,

        /**
         * The player draws a card if he conquered a territory and the game checks if
         * his objective has been completed.
         */
        END_TURN
    }

    /**
     * @return the current phase of the turn
     */
    Phase getCurrentPhase();

    /**
     * Switches to the next phase.
     */
    void switchToNextPhase();

}
