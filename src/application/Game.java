package application;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Game {

    public static final int SPRITE_SIZE = 20;
    public static final int HIT_BOX_SIZE = 15;

    private final double CANVAS_WIDTH;
    private final double CANVAS_HEIGHT;

    private double playerX;
    private double playerY;
    private final ArrayList<Position> treePositions = new ArrayList<>();

    private final int maxTrees;
    private final double playerSpeed;
    private final double treeSpawnProbability;
    private double treeSpeed;
    private final double treeSpeedIncrease;

    private final BooleanProperty doneProperty = new SimpleBooleanProperty(false);
    private int score = 0;

    public Game(int maxTrees, double playerSpeed, double treeSpawnProbability, double treeSpeed,
            double treeSpeedIncrease, double width, double height) {
        this.maxTrees = maxTrees;
        this.playerSpeed = playerSpeed;
        this.treeSpawnProbability = treeSpawnProbability;
        this.treeSpeed = treeSpeed;
        this.treeSpeedIncrease = treeSpeedIncrease;

        CANVAS_HEIGHT = height;
        CANVAS_WIDTH = width;

        playerX = CANVAS_WIDTH / 2 - SPRITE_SIZE / 2;
        playerY = CANVAS_HEIGHT - SPRITE_SIZE - 10;
    }

    /**
     * Advances the game state by 1 frame.
     *
     * @param move
     *            The player's move. -1 and 1 to move left and right and 0 to
     *            not move
     * @return true if the game is over; false otherwise
     */
    public void advanceFrame(int move) {
        treePositions.forEach(pos -> pos.setY(pos.getY() + treeSpeed));
        playerX += move * playerSpeed;
        playerX = playerX < 0 ? 0 : playerX;
        playerX = playerX + SPRITE_SIZE > CANVAS_WIDTH ? CANVAS_WIDTH - SPRITE_SIZE : playerX;
        updateTrees();
        doneProperty.set(checkCollision());
    }

    private void updateTrees() {
        int before = treePositions.size();
        treePositions.removeIf(pos -> pos.getY() > CANVAS_HEIGHT);
        int removed = before - treePositions.size();
        score += removed;
        treeSpeed += treeSpeedIncrease * removed;
        treePositions.forEach(pos -> pos.setY(pos.getY() + treeSpeed));
        if (treePositions.size() < maxTrees && Math.random() < treeSpawnProbability) {
            double randX = Math.random() * (CANVAS_WIDTH - SPRITE_SIZE);
            treePositions.add(new Position(randX, 0 - SPRITE_SIZE));
        }
    }

    private boolean checkCollision() {
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

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public ArrayList<Position> getTreePositions() {
        return treePositions;
    }

    public BooleanProperty doneProperty() {
        return doneProperty;
    }

    public boolean isDone() {
        return doneProperty.get();
    }

    public int getScore() {
        return score;
    }

    public class Position {
        private double x;
        private double y;

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
