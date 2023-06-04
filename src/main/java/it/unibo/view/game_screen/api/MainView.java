package it.unibo.view.game_screen.api;

/**
 * This interface defines the start of the game engine.
 */
public interface MainView {

    /**
     * Starts the game engine.
     */
    void startGame();

    /**
     * Starts the main menu.
     */
    void startMainMenu();

    void changeToGamePanel();

    void closeView();

    void reveal();
}