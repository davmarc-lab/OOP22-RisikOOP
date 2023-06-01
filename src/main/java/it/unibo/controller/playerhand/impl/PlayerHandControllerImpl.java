package it.unibo.controller.playerhand.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unibo.controller.playerhand.api.PlayerHandController;
import it.unibo.model.army.api.Army;
import it.unibo.model.army.api.Army.ArmyType;
import it.unibo.model.army.impl.ArmyImpl;
import it.unibo.model.player.api.Player;

public class PlayerHandControllerImpl implements PlayerHandController {

    private final Player model;
    private final List<Army> inputCards = new ArrayList<>();

    public PlayerHandControllerImpl(final Player model) {
        this.model = model;
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

    private ArmyType getArmyTypeFromString(String name) {
        return Arrays.stream(ArmyType.values()).filter(t -> t.getName().equals(name)).findAny().get();
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

}
