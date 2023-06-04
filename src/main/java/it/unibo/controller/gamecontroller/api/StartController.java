package it.unibo.controller.gamecontroller.api;

import it.unibo.view.game_screen.api.MainView;

public interface StartController {
    void startView();

    void closeView();

    void startGame();

    MainView getView();

    MainController getMainController();
}
