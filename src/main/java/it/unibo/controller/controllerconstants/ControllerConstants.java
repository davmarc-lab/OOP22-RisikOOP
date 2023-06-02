package it.unibo.controller.controllerconstants;

public final class ControllerConstants {

    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));
}
