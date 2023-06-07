package it.unibo.controller.playerhand.api;
 
import java.util.List;
 
import it.unibo.model.army.api.Army;
import it.unibo.model.army.api.Army.ArmyType;
 
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
     * Tries to play cards. Cards can only be played 3 at a time.
     * Retrives the cards combination result.
     * 
     * @return the bonus troops
     */
    int attemptPlayCards();

    /**
     * Retrives the card name from {@link ArmyType} enum.
     * 
     * @param index the value index
     * @return the card name
     */
    String getCardName(int index);

    /**
     * Retrives the number of cards inserted by the user.
     * 
     * @param name the card name
     * @return the number of cards
     */
    int getInputCards(String name);
 
    /**
     * Counts how many cards of a certain type the player has selected.
     * 
     * @param cardType the card type
     * @return the number of selected cards whose type is the specified type
     */
    int getNumberOfCards(String cardType);
 
    /**
     * @return the message that contains the result of an attempt to play cards
     */
    String getMessage();
 
    /**
     * Updates the view of the hand of the player.
     */
    void updateView();
}
