package it.unibo.controller.reader.api;

import java.util.logging.Logger;

/**
 * Generic interface that reads from file.
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
     *Retrieves the logger of the reader which implements this interface.
     * 
     * @return the logger of the reader.
     */
    Logger getLogger();

    /**
     *Retrieves the path to the file from which it reads.
     * 
     * @return the file path.
     */
    String getFilePath();
}
