package com.mateoi.ski;

import java.util.List;

/**
 * An AI player that plays Ski
 *
 * @author mateo
 *
 */
public class AIPlayer implements Player {

    public AIPlayer() {
        // Nothing here
    }

    @Override
    public int move(SkiGame state) {
        Position player = new Position(state.getPlayerX(), state.getPlayerY());
        Position closest = closestTree(state.getTreePositions(), player);
        if (closest == null) {
            return 0;
        } else if (closest.getX() <= state.getHitBoxSize()) {
            return 1;
        } else if (closest.getX() >= state.getFieldWidth() - 2 * state.getHitBoxSize()) {
            return -1;
        } else {
            return (int) Math.signum(player.getX() - closest.getX());
        }
    }

    /**
     * Calculate the position of the closest tree by euclidean distance
     *
     * @param trees
     * @param player
     * @return
     */
    private Position closestTree(List<Position> trees, Position player) {
        double minDistance = Double.MAX_VALUE;
        Position closest = null;
        for (Position tree : trees) {
            double dist = distanceSquared(tree, player);
            if (dist < minDistance) {
                minDistance = dist;
                closest = tree;
            }
        }
        return closest;
    }

    /**
     * Calculate the square of the distance between the two given positions.
     * 
     * @param tree
     * @param player
     * @return
     */
    private double distanceSquared(Position tree, Position player) {
        double dx = tree.getX() - player.getX();
        double dy = tree.getY() - player.getY();
        return Math.abs(dx * dx + dy * dy);
    }
}
