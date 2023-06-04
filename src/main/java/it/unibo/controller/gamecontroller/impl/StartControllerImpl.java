package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.view.game_screen.api.MainView;
import it.unibo.view.game_screen.impl.MainFrame;

/**
 * Implementation of {@link StartController} interface.
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
        this.mainController.startLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainController getMainController() {
        return this.mainController;
    }
}
