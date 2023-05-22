package it.unibo.controller;

import java.util.logging.Logger;

public abstract class AbstractJsonReader<T> {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String CONFIG_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources")
            .append(PATH_SEPARATOR).append("config").append(PATH_SEPARATOR));

    private final Logger logger;
    private final String finalPath;

    public AbstractJsonReader(final String path, final String fileName) {
        this.logger = Logger.getLogger(AbstractJsonReader.class.getName());
        this.finalPath = new String(new StringBuilder(CONFIG_PATH)
                .append(path).append(PATH_SEPARATOR).append(fileName));
    }

    public abstract T readFromJSON();

    public Logger getLogger() {
        return this.logger;
    }

    public String getFilePath() {
        return this.finalPath;
    }
}
