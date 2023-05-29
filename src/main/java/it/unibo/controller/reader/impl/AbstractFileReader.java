package it.unibo.controller.reader.impl;

import java.util.logging.Logger;

import it.unibo.controller.reader.api.FileReader;

/**
 * The abstract class that implements the file reader.
 * 
 * @param <T> the return type of reading method
 */
public abstract class AbstractFileReader<T> implements FileReader<T> {

    private final Logger logger;
    private final String finalPath;

    /**
     * Creates the logger and the path of the file.
     * 
     * @param pathToFile
     *                  the file path
     */
    public AbstractFileReader(final String pathToFile) {
        this.logger = Logger.getLogger(AbstractFileReader.class.getName());
        this.finalPath = new String(pathToFile);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public abstract T readFromFile();

    /**
     * 
     * {@inheritDoc}
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public String getFilePath() {
        return this.finalPath;
    }
}
