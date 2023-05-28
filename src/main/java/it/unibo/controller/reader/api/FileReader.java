package it.unibo.controller.reader.api;

import java.util.logging.Logger;

public interface FileReader<T> {

    T readFromFile();

    Logger getLogger();

    String getFilePath();
}
