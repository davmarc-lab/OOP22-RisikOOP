package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.view.game_screen.api.MainView;
import it.unibo.view.game_screen.impl.MainFrame;

public class StartControllerImpl implements StartController {
    
    private MainView view;
    private MainController mainController;

    public StartControllerImpl() {
        this.view = new MainFrame(this);
        this.view.startMainMenu();
    }

    @Override
    public void startView() {
        this.view.reveal();
    }

    @Override
    public void closeView() {
        this.view.closeView();
    }

    @Override
    public void startGame() {
        this.mainController = new MainControllerImpl(this);
        this.view.changeToGamePanel();
        this.mainController.startLoop();
    }

    @Override
    public MainView getView() {
        return this.view;
    }

    @Override
    public MainController getMainController() {
        return this.mainController;
    }
}
