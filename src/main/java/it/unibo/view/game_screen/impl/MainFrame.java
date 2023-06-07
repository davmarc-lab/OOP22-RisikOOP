package it.unibo.view.game_screen.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.view.game_screen.MainPanel;
import it.unibo.view.game_screen.api.MainView;

/**
 * Implementation of {@link MainView} interface.
 * Provides methods to start and close views.
 */
public class MainFrame extends JFrame implements MainView, Cloneable {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "RisikOOP";

    private final transient StartController controller;

    /**
     * Creates the main frame.
     * 
     * @param startController the start controller of the game
     */
    public MainFrame(final StartController startController) {
        super(FRAME_NAME);
        this.controller = startController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        controller.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMainMenu() {
        this.getContentPane().add(new MainPanel(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeToGamePanel() {
        this.changePanel(this.controller.getMainController().getGamePanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeView() {
        this.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reveal() {
        this.changePanel(new MainPanel(this));
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainFrame clone() throws CloneNotSupportedException {
        return (MainFrame) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MainView getCopy() {
        try {
            return (MainView) this.clone();
        } catch (CloneNotSupportedException e) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Cannot create the copy of the object.");
        }
        throw new IllegalCallerException("Cannot create a copy");
    }

    /**
     * Changes the current panel with the given one.
     * 
     * @param panel the panel to be shown
     */
    private void changePanel(final JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
