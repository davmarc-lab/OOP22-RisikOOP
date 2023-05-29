package it.unibo.controller.combat.impl;

import java.util.Optional;

import it.unibo.common.Pair;
import it.unibo.controller.combat.api.CombatController;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.combat.api.CombatView;
import it.unibo.view.combat.impl.CombatFrame;

public class CombatControllerView implements CombatController {

    private CombatView frame;
    private final Pair<Player, Territory> model;
    private int value;
    private Optional<Integer> finalResult = Optional.empty();

    public CombatControllerView(Pair<Player, Territory> model) {
        this.model = model;
        this.value = 1;
        this.frame = new CombatFrame(this);
    }

    @Override
    public void startPopup() {
        this.frame.startPopup();
    }

    @Override
    public CombatView getFrame() {
        return this.frame;
    }

    @Override
    public void setFrame(CombatView frame) {
        this.frame = frame;
    }

    @Override
    public void addValue(int n) {
        if (isNumberValid(this.value + n)) {
            this.value += n;
        }
    }

    @Override
    public boolean isNumberValid(int value) {
        return value <= this.getSecondObject().getTroops() - 1 && value >= 1;
    }

    @Override
    public void setValue() {
        if (isNumberValid(this.value)) {
            this.finalResult = Optional.of(this.value);
        }
    }

    @Override
    public int getFinalResult() {
        if (this.finalResult.isPresent()) {
            return this.finalResult.get();
        }
        // logger here
        return 0;
    }

    @Override
    public Player getFirstObject() {
        return this.model.getX();
    }

    @Override
    public Territory getSecondObject() {
        return this.model.getY();
    }

}
