package it.unibo.view.viewconstants;

/**
 * This utility class that contains the constants used by the view.
 */
public final class ViewConstants {

    /**
     * Separator based on the OS.
     */
    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    /**
     * Path of "resources" folder.
     */
    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));

    /**
     * Empty constructor.
     */
    private ViewConstants() {
    }
}
