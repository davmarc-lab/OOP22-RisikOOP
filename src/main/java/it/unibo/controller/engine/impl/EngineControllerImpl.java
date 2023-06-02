package it.unibo.controller.engine.impl;

import it.unibo.controller.engine.api.EngineController;
import it.unibo.start.Engine;

/**
 * Implementation of EngineController.
 */
public class EngineControllerImpl implements EngineController {

    private final Engine engine;

    public EngineControllerImpl(final Engine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void restartEngine() {
        this.engine.createController();
        this.engine.startApp();
    }

}
