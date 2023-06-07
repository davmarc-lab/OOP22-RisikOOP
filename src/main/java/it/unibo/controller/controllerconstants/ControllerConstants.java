package it.unibo.controller.controllerconstants;

/**
 * This utility class contains the constants used by the controllers.
 */
public final class ControllerConstants {

    /**
     * Separator based on the OS.
     */
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    /**
     * Path of "resources" folder.
     */
    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR)
            .append("main")
            .append(PATH_SEPARATOR)
            .append("resources")
            .append(PATH_SEPARATOR));

    /**
     * Empty constructor.
     */
    private ControllerConstants() {
    }
}
