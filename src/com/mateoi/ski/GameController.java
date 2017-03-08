package com.mateoi.ski;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Handles drawing a Ski game onto a Canvas and animates the game at a rate of
 * 60 fps.
 *
 * @author mateo
 */
public class GameController {
    /*
     * Icons from https://icons8.com
     */
    /** Player sprite */
    private Image playerSprite = new Image(this.getClass().getResource("resources/Ski Simulator-48.png").toString());
    /** Tree sprite */
    private Image tree = new Image(this.getClass().getResource("resources/Coniferous Tree-48.png").toString());

    /** The game that's being played */
    private SkiGame game;
    /** The canvas to draw the game on */
    private Canvas canvas;
    /** Context used to draw on the canvas */
    private GraphicsContext gc;
    /** PLayer in control of the skier */
    private Player player;

    /**
     * Creates a new controller that will play the game getting moves from both
     * players and draw it on the canvas
     *
     * @param game
     * @param canvas
     * @param player
     */
    public GameController(SkiGame game, Canvas canvas, Player player) {
        this.game = game;
        this.canvas = canvas;
        this.player = player;
        gc = canvas.getGraphicsContext2D();
        game.doneProperty().addListener((a, b, done) -> {
            if (done) {
                System.out.println(game.getScore());
            }
        });
    }

    /**
     * The graphics loop that handles drawing each frame.
     */
    private AnimationTimer loop = new AnimationTimer() {

        @Override
        public void handle(long now) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(playerSprite, game.getPlayerX(), game.getPlayerY(), SkiGame.SPRITE_SIZE, SkiGame.SPRITE_SIZE);
            for (Position treePos : game.getTreePositions()) {
                gc.drawImage(tree, treePos.getX(), treePos.getY(), SkiGame.SPRITE_SIZE, SkiGame.SPRITE_SIZE);
            }
            gc.fillText(String.valueOf(game.getScore()), 5., 15.);
            int move = player.move(game);
            game.advanceFrame(move);
            if (game.isDone()) {
                this.stop();
            }
        }
    };

    /**
     * Starts the animation loop.
     */
    public void playGame() {
        loop.start();
    }
}
