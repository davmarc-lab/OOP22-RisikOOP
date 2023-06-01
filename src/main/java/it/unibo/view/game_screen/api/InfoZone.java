package it.unibo.view.game_screen.api;

/**
 * The interface that defines the area inside the side zone where player
 * information is shown.
 */
public interface InfoZone {

    /**
     * Gets the parent entity to which it belongs.
     * 
     * @return the {@code Sidezone} in which this zone is placed
     */
    SideZone getParentEntity();

    /**
     * Updates all the components in this zone.
     */
    void updateView();
}
