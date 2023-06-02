package it.unibo.controller.controllerconstants;

/**
 * This class is used to store constants that will be used by other classes
 * (mainly the json readers).
 */
public final class ControllerConstants {

    /**
     * Separator based on the OS.
     */
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    /**
     * Path of "resources" fodler.
     */
    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));

    /**
     * Empty constructor.
     */
    private ControllerConstants() {
    }

}
