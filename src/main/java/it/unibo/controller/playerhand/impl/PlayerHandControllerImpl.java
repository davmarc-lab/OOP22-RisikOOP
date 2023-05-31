package it.unibo.controller.playerhand.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unibo.controller.playerhand.api.PlayerHandController;
import it.unibo.model.army.api.Army.ArmyType;
import it.unibo.model.player.api.Player;

public class PlayerHandControllerImpl implements PlayerHandController {

    private final Player model;
    private final List<ArmyType> inputCards = new ArrayList<>();

    public PlayerHandControllerImpl(final Player model) {
        this.model = model;
    }

    @Override
    public List<ArmyType> getInputCards() {
        return this.inputCards;
    }

    @Override
    public void addInputCard(final ArmyType card) {
        this.inputCards.add(card);
    }

    @Override
    public void clearInputCards() {
        this.inputCards.clear();
    }

    @Override
    public ArmyType getArmyTypeFromString(String name) {
        return Arrays.stream(ArmyType.values()).filter(t -> t.getName().equals(name)).findAny().get();
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
