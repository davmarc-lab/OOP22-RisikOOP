package it.unibo.controller.reader.impl;

import java.util.logging.Logger;

import it.unibo.controller.reader.api.FileReader;

public abstract class AbstractFileReader<T> implements FileReader<T> {

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
