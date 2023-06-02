package it.unibo.view.game_screen.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JButton;

import it.unibo.view.game_screen.api.CustomButton;

/**
 * Implementation of CustomButton.
 */
public final class CustomButtonImpl extends JButton implements CustomButton {

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private Color hoverColor;
    private Color pressedColor;

    /**
     * Basic constructor. Creates a button with the specified values
     * 
     * @param x      x position
     * @param y      y position
     * @param width
     * @param height
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

    @Override
    public void setPressedColor(final Color c) {
        this.pressedColor = c;
    }

    @Override
    public void setHoverColor(final Color c) {
        this.hoverColor = c;
    }

    @Override
    public Color getPressedColor() {
        return this.pressedColor;
    }

    @Override
    public Color getHoverColor() {
        return this.hoverColor;
    }

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
