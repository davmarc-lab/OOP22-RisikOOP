package it.unibo.controller.reader.api;

import java.util.logging.Logger;

/**
 * The generic interface to read from file.
 * 
 * @param <T> the return type of reading method
 */
public interface FileReader<T> {

    /**
     * Generic method to read from file.
     * 
     * @return something generic read from a file.
     */
    T readFromFile();

    /**
     * Gets the logger of the reader which implements this interface.
     * 
     * @return the logger of the reader.
     */
    Logger getLogger();

    /**
     * Gets the path to the file from which it reads.
     * 
     * @return the file path.
     */
    String getFilePath();
}
