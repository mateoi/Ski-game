package application;

import java.util.ArrayList;

import application.Game.Position;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class GameController {
    /**
     * Icons from https://icons8.com
     */

    private Image player = new Image(this.getClass().getResource("resources/Ski Simulator-48.png").toString());
    private Image tree = new Image(this.getClass().getResource("resources/Coniferous Tree-48.png").toString());

    private Game game;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<KeyCode> input = new ArrayList<>();

    public GameController(Game game, Canvas canvas, Scene scene) {
        this.game = game;
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        game.doneProperty().addListener((a, b, done) -> {
            if (done) {
                System.out.println(game.getScore());
            }
        });
        scene.setOnKeyPressed(e -> {
            if (!input.contains(e.getCode())) {
                input.add(e.getCode());
            }
        });
        scene.setOnKeyReleased(e -> input.remove(e.getCode()));
    }

    private AnimationTimer loop = new AnimationTimer() {

        @Override
        public void handle(long now) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(player, game.getPlayerX(), game.getPlayerY(), Game.SPRITE_SIZE, Game.SPRITE_SIZE);
            for (Position treePos : game.getTreePositions()) {
                gc.drawImage(tree, treePos.getX(), treePos.getY(), Game.SPRITE_SIZE, Game.SPRITE_SIZE);
            }
            gc.fillText(String.valueOf(game.getScore()), 5., 15.);
            int move = 0;
            if (input.contains(KeyCode.RIGHT)) {
                move++;
            }
            if (input.contains(KeyCode.LEFT)) {
                move--;
            }
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
