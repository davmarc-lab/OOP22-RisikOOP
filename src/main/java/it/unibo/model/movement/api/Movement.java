package it.unibo.model.movement.api;

/**
 * Provides methods to instance troops movements between the territories of a
 * player and checks the validity of the movement.
 */
public interface Movement {

    /**
     * Checks if the movement is possible.
     * 
     * @return {@code true} if the source territory would have at least one troop
     *         after movement, {@code false} otherwise
     */
    boolean isMovementValid();
}
