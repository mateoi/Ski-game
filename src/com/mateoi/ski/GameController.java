package com.mateoi.ski;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {
    /**
     * Icons from https://icons8.com
     */

    private Image playerSprite = new Image(this.getClass().getResource("resources/Ski Simulator-48.png").toString());
    private Image tree = new Image(this.getClass().getResource("resources/Coniferous Tree-48.png").toString());

    private Game game;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;

    public GameController(Game game, Canvas canvas, Player player) {
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

    private AnimationTimer loop = new AnimationTimer() {

        @Override
        public void handle(long now) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(playerSprite, game.getPlayerX(), game.getPlayerY(), Game.SPRITE_SIZE, Game.SPRITE_SIZE);
            for (Position treePos : game.getTreePositions()) {
                gc.drawImage(tree, treePos.getX(), treePos.getY(), Game.SPRITE_SIZE, Game.SPRITE_SIZE);
            }
            gc.fillText(String.valueOf(game.getScore()), 5., 15.);
            int move = player.move(game);
            game.advanceFrame(move);
            if (game.isDone()) {
                this.stop();
            }

        }
    };

    public void playGame() {
        loop.start();
    }
}
