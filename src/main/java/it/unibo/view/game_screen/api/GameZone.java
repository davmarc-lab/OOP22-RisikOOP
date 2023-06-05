package it.unibo.view.game_screen.api;

/**
 * This interface models the zone (in our case a JPanel) that contains the board
 * and the sidebar.
 */
public interface GameZone {

    /**
     * @return the board gui
     */
    BoardZone getBoard();

    /**
     * @return sidebar gui
     */
    SideZone getSideBar();

}
