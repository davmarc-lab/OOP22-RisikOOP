package it.unibo.view.game_screen.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JButton;

import it.unibo.view.game_screen.api.CustomButton;

/**
 * Implementation of {@link CustomButton}.
 * Models a custom button.
 */
public class CustomButtonImpl extends JButton implements CustomButton {

    private static final long serialVersionUID = 1L;

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Transparent color of the button.
     */
    private final Color hoverColor;

    /**
     * Gray color of the button when pressed.
     */
    private final Color pressedColor;

    /**
     * Basic constructor.
     * Creates a button with the specified values
     * 
     * @param x      x position
     * @param y      y position
     * @param width  width of the button
     * @param height height of the button
     */
    public CustomButtonImpl(final int x, final int y, final int width, final int height) {
        super();
        this.hoverColor = TRANSPARENT;
        this.pressedColor = Color.DARK_GRAY;
        this.setBounds(x, y, width, height);
        this.setOpaque(false);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(hoverColor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
