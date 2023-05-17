package it.unibo.model.player.impl;

import it.unibo.model.player.api.ColorPlayer;

/**
 * Implementation of {@code ColorPlayer} interface.
 */
public class ColorPlayerImpl implements ColorPlayer {

    private static final int MAX_VALUE = 255;

    private int r;
    private int g;
    private int b;

    /**
     * This constructor creates a {@code ColorPlayer} giving RGB int values.
     * 
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public ColorPlayerImpl(final int r, final int g, final int b) {
        if (r > MAX_VALUE || g > MAX_VALUE || b > MAX_VALUE
            || r < 0 || g < 0 || b < 0) {
            throw new IllegalArgumentException("Incorrect values for RGB standard (0 - 255)");
        }
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * This constructor creates a {@code ColorPlayer} giving a {@code ColorPlayer}.
     * 
     * @param color new {@code ColorPlayer} object
     */
    public ColorPlayerImpl(final ColorPlayer color) {
        this(color.getRedValue(), color.getGreenValue(), color.getBlueValue());
    }

    /**
     * Creates a standard {@code ColorPlayer} with values 0. 
     */
    public ColorPlayerImpl() {
        this(0, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorPlayer getColor() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(final ColorPlayer c) {
        this.r = c.getRedValue();
        this.g = c.getGreenValue();
        this.b = c.getBlueValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRedValue() {
        return this.r;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGreenValue() {
        return this.g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBlueValue() {
        return this.b;
    }

    /**
     * Simple toString of the color values.
     */
    @Override
    public String toString() {
        return new String(new StringBuilder("Red -> ").append(this.getRedValue()).append(", Green -> ")
        .append(this.getGreenValue()).append(", Blue -> ").append(this.getBlueValue()));
    }
}
