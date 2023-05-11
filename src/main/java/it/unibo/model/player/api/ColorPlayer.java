package it.unibo.model.player.api;

import it.unibo.model.player.impl.ColorPlayerImpl;

/**
 * This interface create a standard RGB color giving RGB values.
 */
public interface ColorPlayer {

    /**
     * Black color (0, 0, 0).
     */
    ColorPlayer BLACK = new ColorPlayerImpl(0, 0, 0);
    /**
     * White color (255, 255, 255).
     */
    ColorPlayer WHITE = new ColorPlayerImpl(255, 255, 255);
    /**
     * Red color (255, 0, 0).
     */
    ColorPlayer RED = new ColorPlayerImpl(255, 0, 0);
    /**
     * Green color (0, 255, 0).
     */
    ColorPlayer GREEN = new ColorPlayerImpl(0, 255, 0);
    /**
     * Blue color (0, 0, 255).
     */
    ColorPlayer BLUE = new ColorPlayerImpl(0, 0, 255);
    /**
     * Yellow color (255, 255, 0).
     */
    ColorPlayer YELLOW = new ColorPlayerImpl(255, 255, 0);

    /**
     * This method returns the current {@code ColorPlayer}.
     * 
     * @return an object color
     */
    ColorPlayer getColor();

    /**
     * This method modifies the current color with a new one.
     * 
     * @param c new {@code ColorPlayer} object
     */
    void setColor(ColorPlayer c);

    /**
     * This methods return the R value of the color.
     * 
     * @return red value
     */
    int getRedValue();

    /**
     * This methods return the G value of the color.
     * 
     * @return green value
     */
    int getGreenValue();

    /**
     * This methods return the B value of the color.
     * 
     * @return blue value
     */
    int getBlueValue();
}
