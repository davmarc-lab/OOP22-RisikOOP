package it.unibo.controller.reader.impl;

import java.util.logging.Logger;

import it.unibo.controller.reader.api.FileReader;

/**
 * Abstract class that implements {@link FileReader}.
 * 
 * @param <T> the return type of reading method
 */
public abstract class AbstractFileReader<T> implements FileReader<T> {

    private final String finalPath;

    /**
     * Creates the logger and the path of the file.
     * 
     * @param pathToFile the file path
     */
    public AbstractFileReader(final String pathToFile) {
        this.finalPath = pathToFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract T readFromFile();

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger() {
        return Logger.getLogger(AbstractFileReader.class.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFilePath() {
        return new StringBuilder(this.finalPath).toString();
    }
}
