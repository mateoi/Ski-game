package com.mateoi.ski;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * This class represents the game state and rules for a game of Ski.
 *
 * @author mateo
 *
 */
public class SkiGame {

    /** The size of the rendered sprites */
    public static final int SPRITE_SIZE = 20;
    /** Size of the hit boxes used to check for collisions */
    public static final int HIT_BOX_SIZE = 15;

    /** Width of the playing area */
    private final double CANVAS_WIDTH;
    /** Height of the playing area */
    private final double CANVAS_HEIGHT;

    /** Position of the Player */
    private Position playerPosition;
    /** List of the positions of all trees currently in play */
    private final ArrayList<Position> treePositions = new ArrayList<>();

    /** Maximum number of trees allowed in the playing field */
    private final int maxTrees;
    /** Speed at which the player moves */
    private final double playerSpeed;
    /** Probability that a new tree will spawn each frame */
    private final double treeSpawnProbability;
    /** Speed at which trees approach the player */
    private double treeSpeed;
    /** How much the trees' speed increases when the player clears a tree */
    private final double treeSpeedIncrease;

    /** Property that holds whether the game is over */
    private final BooleanProperty doneProperty = new SimpleBooleanProperty(false);
    /** Total number of trees cleared */
    private int score = 0;

    /**
     * Create a new game with the given parameters.
     *
     * @param maxTrees
     *            Maximum number of trees allowed in the playing field
     * @param playerSpeed
     *            Speed at which the player moves
     * @param treeSpawnProbability
     *            Probability that a new tree will spawn each frame
     * @param treeSpeed
     *            Speed at which trees approach the player
     * @param treeSpeedIncrease
     *            How much the trees' speed increases when the player clears a
     *            tree
     * @param width
     *            Width of the playing area
     * @param height
     *            Height of the playing area
     */
    public SkiGame(int maxTrees, double playerSpeed, double treeSpawnProbability, double treeSpeed,
            double treeSpeedIncrease, double width, double height) {
        this.maxTrees = maxTrees;
        this.playerSpeed = playerSpeed;
        this.treeSpawnProbability = treeSpawnProbability;
        this.treeSpeed = treeSpeed;
        this.treeSpeedIncrease = treeSpeedIncrease;

        CANVAS_HEIGHT = height;
        CANVAS_WIDTH = width;

        double playerX = CANVAS_WIDTH / 2 - SPRITE_SIZE / 2;
        double playerY = CANVAS_HEIGHT - SPRITE_SIZE - 10;
        playerPosition = new Position(playerX, playerY);
    }

    /**
     * Advances the game state by one frame.
     *
     * @param move
     *            The player's move. -1 and 1 to move left and right and 0 to
     *            not move
     */
    public void advanceFrame(int move) {
        double playerX = playerPosition.getX();
        playerX += move * playerSpeed;
        playerX = playerX < 0 ? 0 : playerX;
        playerX = playerX + SPRITE_SIZE > CANVAS_WIDTH ? CANVAS_WIDTH - SPRITE_SIZE : playerX;
        playerPosition.setX(playerX);
        updateTrees();
        doneProperty.set(checkCollision());
    }

    /**
     * Moves the trees down, removes trees that have moved past the player, and
     * may spawn a new tree if there are less than maxTrees trees in play.
     */
    private void updateTrees() {
        treePositions.forEach(pos -> pos.setY(pos.getY() + treeSpeed));
        int before = treePositions.size();
        treePositions.removeIf(pos -> pos.getY() > CANVAS_HEIGHT);
        int removed = before - treePositions.size();
        score += removed;
        treeSpeed += treeSpeedIncrease * removed;
        if (treePositions.size() < maxTrees && Math.random() < treeSpawnProbability) {
            double randX = Math.random() * (CANVAS_WIDTH - SPRITE_SIZE);
            treePositions.add(new Position(randX, 0 - SPRITE_SIZE));
        }
    }

    /**
     * Checks if there is a collision between the player and any tree
     *
     * @return True if there is a collision, false otherwise
     */
    private boolean checkCollision() {
        double playerX = playerPosition.getX();
        double playerY = playerPosition.getY();
        for (Position tree : treePositions) {
            double tx = tree.getX();
            double ty = tree.getY();
            if (playerX + HIT_BOX_SIZE >= tx && playerX <= tx + HIT_BOX_SIZE && playerY + HIT_BOX_SIZE >= ty
                    && playerY <= ty + HIT_BOX_SIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return The x-coordinate of the player
     */
    public double getPlayerX() {
        return playerPosition.getX();
    }

    /**
     * @return The y-coordinate of the player
     */
    public double getPlayerY() {
        return playerPosition.getY();
    }

    /**
     * @return The width of the playing area
     */
    public double getFieldWidth() {
        return CANVAS_WIDTH;
    }

    /**
     * @return The height of the playing area
     */
    public double getFieldHeight() {
        return CANVAS_HEIGHT;
    }

    /**
     * @return The size of the collision box
     */
    public int getHitBoxSize() {
        return HIT_BOX_SIZE;
    }

    /**
     * @return A list of the positions of all the trees
     */
    public ArrayList<Position> getTreePositions() {
        return treePositions;
    }

    /**
     * @return A property that holds whether the game has finished or not
     */
    public BooleanProperty doneProperty() {
        return doneProperty;
    }

    /**
     * @return Ture, if the game is over, False otherwise
     */
    public boolean isDone() {
        return doneProperty.get();
    }

    /**
     * @return The player's current score
     */
    public int getScore() {
        return score;
    }

}
