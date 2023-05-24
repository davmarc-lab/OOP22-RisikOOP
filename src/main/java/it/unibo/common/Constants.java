package it.unibo.common;

public final class Constants {

    public static final int MAX_CARDS_FOR_EACH_PLAYER = 42;

    public static final int MAX_PLAYERS = 3;

    public static final int TROOPS = 21;

    public static final int BONUS_TROOPS_DIVIDER = 3;

    public static final String DISPLACEMENTS_FRAME_NAME = "Displacements";

    public static final String FRAME_NAME = "RisikOOP";

    public static final double MAIN_PANEL_WIDTH_PERC = 0.4;

    public static final double MAIN_PANEL_HEIGHT_PERC = 0.4;

    public static final int MAIN_PANEL_VGAP = 250;

    public static final int MAIN_PANEL_HGAP = 50;

    public static final String MAIN_PANEL_PLAY_LABEL = "Play";

    public static final String MAIN_PANEL_QUIT_LABEL = "Quit";

    public static final String MAIN_PANEL_RULES_LABEL = "Rules";

    public static final String MAIN_PANEL_TITLE_LABEL = "RISIKOOP";

    public static final String PATH_SEPARATOR = System.getProperty("file.separator");

    public static final String RESOURCES_PATH = new String(new StringBuilder("src")
            .append(PATH_SEPARATOR).append("main")
            .append(PATH_SEPARATOR).append("resources").append(PATH_SEPARATOR));

    public static final String OBJECTIVES_PATH = new StringBuilder(RESOURCES_PATH).append("config")
            .append(PATH_SEPARATOR)
            .append("objective").append(PATH_SEPARATOR).append("Objectives.json").toString();

    public static final String TERRITORIES_PATH = new StringBuilder(RESOURCES_PATH).append("config")
            .append(PATH_SEPARATOR).append("territory").append(PATH_SEPARATOR)
            .append("Territories.json").toString();

    public static final String COORDINATES_PATH = new StringBuilder(RESOURCES_PATH).append("config")
            .append(PATH_SEPARATOR).append("territory").append(PATH_SEPARATOR)
            .append("Coordinates.json").toString();

    public static final String RULES_PATH = new StringBuilder(RESOURCES_PATH).append("instructions")
            .append(PATH_SEPARATOR).append("rules.txt").toString();

    public static final String MAP_PATH = new StringBuilder(RESOURCES_PATH).append("images")
            .append(PATH_SEPARATOR).append("RisikoMap.jpg").toString();

    public static final String WALLPAPER_PATH = new StringBuilder(RESOURCES_PATH).append("images")
            .append(PATH_SEPARATOR).append("MenuWallpaper.jpg").toString();
}
