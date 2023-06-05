package it.unibo.view.game_screen.api;

import java.awt.Color;

/**
 * Models a custom button.
 */
public interface CustomButton {

    /**
     * Sets the background color for the button.
     * 
     * @param c color
     */
    void setPressedColor(Color c);

    /**
     * Sets the hover color for the button.
     * 
     * @param c color
     */
    void setHoverColor(Color c);

    /**
     * @return background color
     */
    Color getPressedColor();

    /**
     * @return hover color
     */
    Color getHoverColor();
}
