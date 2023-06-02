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

public class PlayerHandControllerImpl implements PlayerHandController {

    private final Player model;
    private final CardZone view;
    private final List<Army> inputCards = new ArrayList<>();
    private String message;

    public PlayerHandControllerImpl(final Player model, final CardZone view) {
        this.model = model;
        this.view = view;
    }

    private ArmyType getArmyTypeFromString(String name) {
        return Arrays.stream(ArmyType.values()).filter(t -> t.getName().equals(name)).findAny().get();
    }

    @Override
    public List<Army> getInputCards() {
        return this.inputCards;
    }

    @Override
    public void addInputCard(final String card) {
        this.inputCards.add(new ArmyImpl(getArmyTypeFromString(card)));
    }

    @Override
    public void clearInputCards() {
        this.inputCards.clear();
    }

    @Override
    public boolean isInputFull() {
        return this.inputCards.size() == 3;
    }

    @Override
    public boolean isAddPossible(int currentNumber, int input) {
        return input + 1 <= currentNumber;
    }

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

    @Override
    public int getNumberOfCards(String cardType) {
        return (int) this.inputCards.stream().filter(c -> c.getArmyType().getName().equals(cardType)).count();
    }

    @Override
    public int getPlayerFirstCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.INFANTRY)).count();
    }

    @Override
    public int getPlayerSecondCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.CAVALRY)).count();
    }

    @Override
    public int getPlayerThirdCards() {
        return (int) this.model.getPlayerHand().getHand().stream()
                .filter(c -> c.getArmyType().equals(ArmyType.ARTILLERY)).count();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public CardZone getView() {
        return this.view;
    }
}
