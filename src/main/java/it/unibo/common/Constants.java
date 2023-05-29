package it.unibo.common;

public final class Constants {

    public static final int MAX_CARDS_FOR_EACH_PLAYER = 42;

    public static final int MAX_PLAYERS = 3;

    public static final int TROOPS = 21;

    public static final int BONUS_TROOPS_DIVIDER = 3;

    public static final String DISPLACEMENTS_FRAME_NAME = "Displacements";

    public static final String FRAME_NAME = "RisikOOP";

    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));

    public static final String OBJECTIVES_PATH = new StringBuilder(RESOURCES_PATH).append("config")
            .append(PATH_SEPARATOR)
            .append("objective").append(PATH_SEPARATOR).append("Objectives.json").toString();

    public static final String COORDINATES_PATH = new StringBuilder(RESOURCES_PATH).append("config")
            .append(PATH_SEPARATOR).append("territory").append(PATH_SEPARATOR)
            .append("Coordinates.json").toString();

    public static final String MAP_PATH = new StringBuilder(RESOURCES_PATH).append("images")
            .append(PATH_SEPARATOR).append("RisikoMap.jpg").toString();
}
