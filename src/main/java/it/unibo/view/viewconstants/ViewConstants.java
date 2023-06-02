package it.unibo.view.viewconstants;

public final class ViewConstants {

    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));
}
