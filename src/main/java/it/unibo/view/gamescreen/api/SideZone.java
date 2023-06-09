package it.unibo.view.gamescreen.api;

/**
 * Defines the side zone where the player's information and cards are displayed.
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
