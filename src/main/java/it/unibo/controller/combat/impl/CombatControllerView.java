package it.unibo.controller.combat.impl;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.model.combat.api.Combat.Role;
import it.unibo.common.Pair;
import it.unibo.controller.combat.api.CombatController;
import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;
import it.unibo.view.combat.api.CombatView;
import it.unibo.view.combat.impl.CombatFrame;

/**
 * Implementation of {@link CombatController}.
 * It manages combat between players and their territories.
 */
public class CombatControllerView implements CombatController {

    private final CombatView frame;
    private final Pair<Player, Territory> model;
    private int value;
    private final Role role;
    private Optional<Integer> combatOutcome = Optional.empty();
    private boolean isActionRunnig = true;

    /**
     * Constructs a CombatControllerView object.
     *
     * @param model the player and territory pair for the combat
     * @param role  the role of the player involved in combat
     */
    public CombatControllerView(final Pair<Player, Territory> model, final Role role) {
        this.model = model;
        this.value = 1;
        this.role = role;
        this.frame = new CombatFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPopup() {
        this.frame.startPopup(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTroops(final int n) {
        if (isNumberValid(this.value + n)) {
            this.value += n;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNumberValid(final int value) {
        return value <= 3 && this.checkStableTroops(value) && value >= 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCombatOutcome() {
        if (isNumberValid(this.value)) {
            this.combatOutcome = Optional.of(this.value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCombatOutcome() {
        if (this.combatOutcome.isPresent()) {
            return this.combatOutcome.get();
        }
        Logger.getLogger(CombatControllerView.class.getName()).log(Level.WARNING,
                "Cannot use the input given by the user because is not valid.");
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCombatPlayer() {
        return this.model.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Territory getCombatTerritory() {
        return this.model.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelAction() {
        this.isActionRunnig = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActionRunnig() {
        return this.isActionRunnig;
    }

    /**
     * Checks if the selected troops can participate in combat by leaving at least
     * one behind.
     * 
     * @param value the number of troops
     * @return {@code true} if the territory would be left with at least one troop,
     *         {@code false}
     *         otherwise
     */
    private boolean checkStableTroops(final int value) {
        return value <= this.getCombatTerritory().getTroops() - this.role.getStableTroops();
    }
}
