package it.unibo.model.gameloop;

import it.unibo.model.gameloop.api.PhaseManager;
import it.unibo.model.gameloop.api.TurnManager;
import it.unibo.model.gameloop.impl.PhaseManagerImpl;
import it.unibo.model.gameloop.impl.TurnManagerImpl;
import it.unibo.model.gameprep.api.GamePrep;
import it.unibo.model.gameprep.impl.GamePrepImpl;

/**
 * This class is used to process the input received from the view
 * and tell the view what to render.
 */
public class GameLoop {

    private final PhaseManager phaseManager;
    private final TurnManager turnManager;
    private final GamePrep prep;

    /**
     * Prepares all the necessary components.
     */
    public GameLoop() {
        prep = new GamePrepImpl();
        phaseManager = new PhaseManagerImpl();
        turnManager = new TurnManagerImpl(prep.getPlayers());
    }

    /**
     * Checks the current phase of the turn and acts accordingly.
     * 
     * @param input the input received from the view, it can be a Territory, a Card
     *              or an Integer (army)
     */
    public void processInput(final Object input) {
        switch (phaseManager.getCurrentPhase()) {
            case PREPARATION:
                // TODO dare le armate al giocatore -> obj è Territory
                phaseManager.switchToNextPhase();
                break;
            case PLAY_CARDS:
                // TODO il giocatore gioca le carte -> obj è Card
                phaseManager.switchToNextPhase();
                break;
            case COMBAT:
                // TODO il giocatore sceglie prima un suo territorio poi uno nemico e le armate
                // da usare -> obj è Territory o Integer
                // TODO se il giocatore preme su "termina combattimento" passa alla fase
                // MOVEMENT
                break;
            case MOVEMENT:
                // TODO il il giocatore sceglie 2 suoi territori e le armate da spostare -> obj
                // = Territory o Integer
                // TODO se il giocatore preme su "termina turno" passa alal fase END_TURN
                break;
            case END_TURN:
                // TODO pesca una carta se il player ha fatto una conquista
                phaseManager.switchToNextPhase();
                turnManager.switchToNextPlayer();
                break;
            default:
                break;
        }
    }

}
