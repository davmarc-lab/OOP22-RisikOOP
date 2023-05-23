package it.unibo.controller;

import java.util.logging.Logger;

public abstract class AbstractFileReader<T> {

    protected static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String CONFIG_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));

    private final Logger logger;
    private final String finalPath;

    public AbstractFileReader(final String pathToFile) {
        this.logger = Logger.getLogger(AbstractFileReader.class.getName());
        this.finalPath = new String(new StringBuilder(CONFIG_PATH)
                .append(pathToFile));
        System.out.println(finalPath);
    }

    public abstract T readFromFile();

    public Logger getLogger() {
        return this.logger;
    }

    public String getFilePath() {
        return this.finalPath;
    }
}
