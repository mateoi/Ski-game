package com.mateoi.ski;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class SkiFXApp extends Application {

    private static SkiGame game;

    private static Player player;

    private static ArrayList<KeyCode> input = new ArrayList<>();

    public SkiFXApp() {
        // Nothing here
    }

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
            Canvas canvas = new Canvas(game.getMaxX(), game.getMaxY());
            root.getChildren().add(canvas);
            GameController controller = new GameController(game, canvas, player);
            window.show();
            controller.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addSceneListeners(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (!input.contains(e.getCode())) {
                input.add(e.getCode());
            }
        });
        scene.setOnKeyReleased(e -> input.remove(e.getCode()));
    }

    public static void setGame(SkiGame game) {
        SkiFXApp.game = game;
    }

    public static void setPlayer(Player player) {
        SkiFXApp.player = player;
    }
}
