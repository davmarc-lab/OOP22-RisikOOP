package it.unibo.controller.playerhand.api;

import java.util.List;

import it.unibo.model.army.api.Army;

/**
 * Models a controller used for the player's hand.
 */
public interface PlayerHandController {

    /**
     * @return the list of the cards' types in player's hand
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
     * @return {@code true} if the player selected 3 cards, {@code false} if he
     *         selected 2 or less cards so far
     */
    boolean isInputFull();

    /**
     * Checks if the player can select another card.
     * 
     * @param input         the number of cards added so far
     * @param currentNumber the number of cards that a player possesses
     * @return {@code true} if the player can select another card, {@code false}
     *         otherwise
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
     * Counts how many cards of type {@code INFANTRY} are in the player's
     * hand.
     * 
     * @return the number of cards in the player's hand whose type is
     *         {@code INFANTRY}
     */
    int getPlayerFirstCards();

    /**
     * Counts how many cards of type {@code CAVALRY} are in the player's hand.
     * 
     * @return the number of cards in the player's hand whose type is
     *         {@code CAVALRY}
     */
    int getPlayerSecondCards();

    /**
     * Counts how many cards of type {@code ARTILLERY} are in the player's hand.
     * 
     * @return the number of cards in the player's hand whose type is
     *         {@code ARTILLERY}
     */
    int getPlayerThirdCards();

    /**
     * @return the message that contains the result of an attempt to play cards
     */
    String getMessage();

    /**
     * Updates the view of the hand of the player.
     */
    void updateView();
}
