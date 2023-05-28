package it.unibo.view.game_screen;

import java.awt.Color;

/**
 * This interface models a custom button 
 */
public interface CustomButton {

    /**
     * Sets the background color for the button.
     * @param c color
     */
    void setPressedColor(Color c);

    /**
     * Sets the hover color for the button.
     * @param c color
     */
    void setHoverColor(Color g);

    /**
     * @return background color
     */
    Color getPressedColor();

    /**
     * @return hover color
     */
    Color getHoverColor();

}
