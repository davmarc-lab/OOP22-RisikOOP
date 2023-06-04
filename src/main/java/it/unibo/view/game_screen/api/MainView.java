package it.unibo.view.game_screen.api;

/**
 * This interface provides methods to start and close some view like the main
 * menu and the game view.
 */
public interface MainView {

    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Instances the main menu.
     */
    void startMainMenu();

    /**
     * Changes the view.
     */
    void changeToGamePanel();

    /**
     * Close the current view.
     */
    void closeView();

    /**
     * Sets visible the main menu view.
     */
    void reveal();
}
