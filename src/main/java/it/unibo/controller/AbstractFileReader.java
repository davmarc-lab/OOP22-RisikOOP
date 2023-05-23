package it.unibo.controller;

import java.util.logging.Logger;

public abstract class AbstractFileReader<T> {

    private final Logger logger;
    private final String finalPath;

    public AbstractFileReader(final String pathToFile) {
        this.logger = Logger.getLogger(AbstractFileReader.class.getName());
        this.finalPath = new String(pathToFile);
    }

    public abstract T readFromFile();

    public Logger getLogger() {
        return this.logger;
    }

    public String getFilePath() {
        return this.finalPath;
    }
}
