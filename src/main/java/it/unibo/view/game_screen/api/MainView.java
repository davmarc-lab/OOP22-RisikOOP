package it.unibo.view.game_screen.api;

/**
 * Provides methods to start and close views.
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

    /**
     * Retrieves a copy of the MainView.
     * 
     * @return a copy of the MainView
     */
    MainView getCopy();
}
