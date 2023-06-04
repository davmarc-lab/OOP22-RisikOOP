package it.unibo.controller.gamecontroller.api;

import it.unibo.view.game_screen.api.MainView;

/**
 * The {@code StartControllerInterface} provides method to control the Main Menu
 * of the application, it also permit to start the game.
 */
public interface StartController {
    /**
     * Initialiaze and show the Main Menu view.
     */
    void startView();

    /**
     * Close the Main Menu view.
     */
    void closeView();

    /**
     * Initialize the {@link MainController} of the application and starts the game.
     */
    void startGame();

    /**
     * Retrives the Main Menu view.
     * 
     * @return main manu view.
     */
    MainView getView();

    /**
     * Retrieves the {@link MainController} of the application.
     * 
     * @return application main controller
     */
    MainController getMainController();
}
