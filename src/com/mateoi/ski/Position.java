package com.mateoi.ski;

/**
 * Convenience class that holds an x and a y coordinate.
 *
 * @author mateo
 *
 */
public class Position {
    /** X-coordinate of this position */
    private double x;
    /** Y-coordinate of this position */
    private double y;

    /**
     * Create a new Position.
     *
     * @param x
     * @param y
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X-coordinate of this Position
     */
    public double getX() {
        return x;
    }

    /**
     * @return The Y-coordinate of this Position
     */
    public double getY() {
        return y;
    }

    /**
     * Change the X-coordinate of this Position
     * 
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Change the Y-coordinate of this Position
     * 
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}
