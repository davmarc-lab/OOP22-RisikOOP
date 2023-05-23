package it.unibo.controller.popup;

import it.unibo.model.player.api.Player;

public class PlayerPopupController {
    private final Player player;

    public PlayerPopupController(final Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return this.player;
    }
}
