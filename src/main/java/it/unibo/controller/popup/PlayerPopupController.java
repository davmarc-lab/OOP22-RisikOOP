package it.unibo.controller.popup;

import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

public class PlayerPopupController {

    private final Player player;
    private final Territory terr;

    public PlayerPopupController(final Player p, final Territory terr) {
        this.player = p;
        this.terr = terr;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Territory getTerritory() {
        return this.terr;
    }
}
