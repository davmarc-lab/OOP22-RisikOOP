package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.view.gamescreen.api.MainView;
import it.unibo.view.gamescreen.impl.MainFrame;

/**
 * Implementation of {@link StartController} interface.
 * It provides method to control the Main Menu
 * of the application, it also permit to start the game.
 */
public class StartControllerImpl implements StartController {

    private final MainView view;
    private MainController mainController;

    /**
     * Construct an instance of StartControllerImpl.
     */
    public StartControllerImpl() {
        this.view = new MainFrame(this);
        this.view.startMainMenu();
        this.mainController = new MainControllerImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startView() {
        this.view.reveal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeView() {
        this.view.closeView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.mainController = new MainControllerImpl(this);
        this.view.changeToGamePanel();
        this.mainController.startEngine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainController getMainController() {
        return this.mainController.getCopy();
    }
}
