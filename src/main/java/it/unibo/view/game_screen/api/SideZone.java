package it.unibo.view.game_screen.api;

/**
 * Defines the side zone.
 */
public interface SideZone {

    /**
     * Gets action buttons zone.
     * 
     * @return the {@code ButtonZone} that is included in this zone
     */
    ButtonZone getButtonPanel();

    /**
     * Gets zone where the cards can be played.
     * 
     * @return the {@code CardZone} that is included in this zone
     */
    CardZone getCardPanel();

    /**
     * Gets player's information zone.
     * 
     * @return the {@code InfoZone} that is included in this zone
     */
    InfoZone getInfoPanel();

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

    /**
     * @return a copy of SideZone
     */
    SideZone getCopy();
}
