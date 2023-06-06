package it.unibo.view.game_screen.api;

/**
 * Defines the side zone.
 */
public interface SideZone {

    /**
     * Update the {@code InfoZone}.
     */
    void updateInfo();

    /**
     * Update the {@code CardZone}.
     */
    void updateCards();

    /**
     * Set the {@code PlayerHandController} for {@code CardZone}.
     */
    void setCardController();
}
