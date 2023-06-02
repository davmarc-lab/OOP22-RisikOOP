package it.unibo.view.game_screen.api;

import it.unibo.controller.gamecontroller.api.MainController;

/**
 * This interface models the zone (in our case a JPanel) that contains the board
 * and the side bar.
 */
public interface GameZone {

    /**
     * @return the board gui
     */
    BoardZone getBoard();

    /**
     * @return side bar gui
     */
    SideZone getSideBar();

    /**
     * @return the controller
     */
    MainController getController();

}
