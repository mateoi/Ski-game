package com.mateoi.ski;

/**
 * Interface for a player that plays Ski
 * 
 * @author mateo
 *
 */
public interface Player {
    /**
     * Returns a move (-1 and 1 for left and right, 0 for no input) given a game
     * state to analyze.
     * 
     * @param state
     * @return The move to perform
     */
    public int move(SkiGame state);
}
