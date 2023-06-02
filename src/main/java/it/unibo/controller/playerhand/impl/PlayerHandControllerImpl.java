package it.unibo.controller.playerhand.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unibo.controller.playerhand.api.PlayerHandController;
import it.unibo.model.army.api.Army;
import it.unibo.model.army.api.Army.ArmyType;
import it.unibo.model.army.impl.ArmyImpl;
import it.unibo.model.player.api.Player;
import it.unibo.view.game_screen.api.CardZone;

/**
 * Implementation of the controller for the player's hand.
 */
public class PlayerHandControllerImpl implements PlayerHandController {

    private final Player model;
    private final CardZone view;
    private final List<Army> inputCards = new ArrayList<>();
    private String message;

    /**
     * Constructor that sets the necessary parameters.
     * 
     * @param model the player that will use this controller
     * @param view  the view that will contain the player's hand
     */
    public PlayerHandControllerImpl(final Player model, final CardZone view) {
        this.model = model;
        this.view = view;
    }

    /**
     * @param name the name of the card's type
     * @return the card type
     */
    private ArmyType getArmyTypeFromString(final String name) {
        return Arrays.stream(ArmyType.values()).filter(t -> t.getName().equals(name)).findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Army> getInputCards() {
        return this.inputCards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInputCard(final String card) {
        this.inputCards.add(new ArmyImpl(getArmyTypeFromString(card)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearInputCards() {
        this.inputCards.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInputFull() {
        return this.inputCards.size() == 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAddPossible(final int currentNumber, final int input) {
        return input + 1 <= currentNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attemptPlayCards() {
        final int bonusTroops = this.model.getPlayerHand().playCards(this.inputCards);
        if (bonusTroops > 0) {
            this.message = new StringBuilder("Cards valid, added ").append(bonusTroops).append(" troops.").toString();
            this.model.addTroops(bonusTroops);
            this.model.removeCardsToPlayerHand(this.inputCards);
        } else {
            this.message = new StringBuilder("Cards invalid, operation aborted.").toString();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfCards(final String cardType) {
        return (int) this.inputCards.stream().filter(c -> c.getArmyType().getName().equals(cardType)).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerFirstCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.INFANTRY)).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerSecondCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.CAVALRY)).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerThirdCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.ARTILLERY)).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardZone getView() {
        return this.view;
    }

}
