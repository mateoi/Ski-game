package com.mateoi.ski;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * This class is the FX application that gets rendered on screen. Also maintains
 * a list of currently pressed keys for keyboard input.
 *
 * @author mateo
 *
 */

public class SkiFXApp extends Application {

    private static SkiGame game;

    private static Player player;

    private static ArrayList<KeyCode> input = new ArrayList<>();

    public SkiFXApp() {
        // Nothing here
    }

    /**
     * Checks if the given {@link KeyCode} is pressed.
     *
     * @param code
     * @return
     */
    public static boolean keyPressed(KeyCode code) {
        return input.contains(code);
    }

    @Override
    public void start(Stage window) throws Exception {
        try {
            Group root = new Group();
            Scene scene = new Scene(root);
            window.setScene(scene);
            addSceneListeners(scene);
            Canvas canvas = new Canvas(game.getFieldWidth(), game.getFieldHeight());
            root.getChildren().add(canvas);
            GameController controller = new GameController(game, canvas, player);
            window.show();
            controller.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds keyboard listeners to the given scene.
     *
     * @param scene
     */
    private static void addSceneListeners(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (!input.contains(e.getCode())) {
                input.add(e.getCode());
            }
        });
        scene.setOnKeyReleased(e -> input.remove(e.getCode()));
    }

    /**
     * Set the state of the game
     *
     * @param game
     */
    public static void setGame(SkiGame game) {
        SkiFXApp.game = game;
    }

    /**
     * Set the player that will play the game.
     *
     * @param player
     */
    public static void setPlayer(Player player) {
        SkiFXApp.player = player;
    }
}
