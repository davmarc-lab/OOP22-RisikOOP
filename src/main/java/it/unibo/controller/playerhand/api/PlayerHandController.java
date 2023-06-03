package it.unibo.controller.playerhand.api;

import java.util.List;

import it.unibo.model.army.api.Army;
import it.unibo.view.game_screen.api.CardZone;

/**
 * This interface models a controller used for the player's hand.
 */
public interface PlayerHandController {

    /**
     * @return the list of the cards' types (player's hand)
     */
    List<Army> getInputCards();

    /**
     * Adds a card to the list of selected cards.
     * 
     * @param card the name of the card's type
     */
    void addInputCard(String card);

    /**
     * Clears the list of selected cards.
     */
    void clearInputCards();

    /**
     * Checks if the player has already selected 3 cards to play.
     * 
     * @return true if the player selected 3 cards, false if he selected 2 or less
     *         cards so far
     */
    boolean isInputFull();

    /**
     * Checks if the player can select another card.
     * 
     * @param input         the number of cards added so far
     * @param currentNumber the number of cards that a player possesses
     * @return true if the player can select another card, false otherwise
     */
    boolean isAddPossible(int input, int currentNumber);

    /**
     * Tries to play cards. Cards can only be played 3 at a time
     */
    void attemptPlayCards();

    /**
     * Counts how many cards of a certain type the player has selected.
     * 
     * @param cardType the card type
     * @return the number of selected cards whose type is the specified type
     */
    int getNumberOfCards(String cardType);

    /**
     * Counts how many cards of the first type (INFANTRY) are in the player's hand.
     * 
     * @return the number of cards in the player's hand whose type is the first
     *         (INFANTRY)
     */
    int getPlayerFirstCards();

    /**
     * Counts how many cards of the second type (CAVALRY) are in the player's hand.
     * 
     * @return the number of cards in the player's hand whose type is the second
     *         (CAVALRY)
     */
    int getPlayerSecondCards();

    /**
     * Counts how many cards of the third type (ARTILLERY) are in the player's hand.
     * 
     * @return the number of cards in the player's hand whose type is the third
     *         (ARTILLERY)
     */
    int getPlayerThirdCards();

    /**
     * @return the message that contains the result of an attempt to play cards
     */
    String getMessage();

    /**
     * @return the GUI that manages the cards
     */
    CardZone getView();

    /**
     * Updates the view of the hand of the player.
     */
    void updateView();

}
