package com.mateoi.ski;

import java.util.List;

public class AiPlayer implements Player {

    public AiPlayer() {
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
        } else if (closest.getX() >= state.getMaxX() - 2 * state.getHitBoxSize()) {
            return -1;
        } else {
            return (int) Math.signum(player.getX() - closest.getX());
        }
    }

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

    private double distanceSquared(Position tree, Position player) {
        double dx = tree.getX() - player.getX();
        double dy = tree.getY() - player.getY();
        return Math.abs(dx * dx + dy * dy);
    }
}
