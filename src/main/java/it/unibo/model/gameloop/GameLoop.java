package it.unibo.model.gameloop;

import it.unibo.controller.api.MainController;
import it.unibo.model.board.api.GameBoard;
import it.unibo.model.board.impl.GameBoardImpl;
import it.unibo.model.gameloop.api.PhaseManager;
import it.unibo.model.gameloop.impl.PhaseManagerImpl;

/**
 * This class is used to process the input received from the view
 * and tell the view what to render.
 */
public class GameLoop {

    private final PhaseManager phaseManager;
    private final GameBoard board;
    private final MainController controller;

    /**
     * Prepares all the necessary components.
     */
    public GameLoop(final MainController controller) {
        this.controller = controller;
        board = new GameBoardImpl();
        phaseManager = new PhaseManagerImpl();
    }

    /**
     * Checks the current phase of the turn and acts accordingly.
     * 
     * @param input the input received from the view, it can be a Territory, a Card
     *              or an Integer (troops)
     */
    public void processInput(final Object input) {
        switch (phaseManager.getCurrentPhase()) {
            case PREPARATION:
                phaseManager.switchToNextPhase();
                break;
            case PLAY_CARDS:
                phaseManager.switchToNextPhase();
                break;
            case COMBAT:
                phaseManager.switchToNextPhase();
                break;
            case MOVEMENT:
                phaseManager.switchToNextPhase();
                break;
            case END_TURN:
                phaseManager.switchToNextPhase();
                board.getTurnManager().switchToNextPlayer();
                break;
            default:
                break;
        }
    }

}
